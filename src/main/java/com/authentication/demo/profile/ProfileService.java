package com.authentication.demo.profile;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Map;

import com.authentication.demo.advice.ForbiddenException;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.amazon.AmazonService;
import com.authentication.demo.profile.request.UpdateProfileRequest;
import com.authentication.demo.profile.request.UploadPDFRequest;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ProfileService {
    @Autowired
    private final AmazonService amazonService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ProfileRepository profileRepository;

    public ProfileService(
            ProfileRepository profileRepository,
            AmazonService amazonService,
            UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.amazonService = amazonService;
        this.userRepository = userRepository;
    }

    public byte[] downloadResume(Long id) {
        Profile profile = this.profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found."));
        return this.amazonService.downloadFile("arrow-date/joblet", profile.getFileName());
    }

    public Profile createProfile() {
        Profile profile = new Profile();

        return this.profileRepository.save(profile);
    }

    public String uploadResume(UploadPDFRequest request, Long id) {
        checkOwnerShip(id, "Cannot upload to another user.");

        return this.amazonService.upload("arrow-date/joblet",
                request.getFile().getOriginalFilename(), request.getFile());
    }

    public Map<String, String> downloadPublicUrlResume(String fileName, Long id) {
        return this.amazonService.getPublicUrl("arrow-date/joblet", fileName);
    }

    public Profile getProfile(Long id) {
        checkOwnerShip(id, "You can only view your own profile.");
        Profile profile = this.profileRepository.getProfile(id);
        return profile;
    }

    public void deleteOldResume(Long id) {
        Profile profile = this.profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found."));
        if (profile.getFileName() != null) {
            this.amazonService.delete("arrow-date/joblet", profile.getFileName());
        }
    }

    public void addResume(String resumeUrl, Long id, String fileName) {
        Profile profile = this.profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found."));
        profile.setResume(resumeUrl);
        profile.setFileName(fileName);
        this.profileRepository.save(profile);
    }

    private void checkOwnerShip(Long id, String message) {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = this.userRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            if (user.getProfile().getId() != id) {
                throw new ForbiddenException(message);
            }
        }
    }

    @Transactional
    public Profile updateProfile(Long id, UpdateProfileRequest request) {

        checkOwnerShip(id, "You cannot edit another person's profile");

        Profile profile = this.profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile was not found."));
        profile.setCity(MyUtils.capitalize(request.getCity()));
        profile.setEmail(request.getEmail());
        profile.setState(request.getState());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setCountry(request.getCountry());
        profile.setResume(request.getResume());

        return this.profileRepository.save(profile);

    }

    public void deleteResume(Long id) {
        Profile profile = getProfile(id);
        this.amazonService.delete("arrow-date/joblet", profile.getFileName());
        profile.setResume(null);
        profile.setFileName(null);

        this.profileRepository.save(profile);
    }
}
