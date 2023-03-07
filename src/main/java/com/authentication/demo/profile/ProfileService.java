package com.authentication.demo.profile;

import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.profile.request.UpdateProfileRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    @Autowired
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile() {
        Profile profile = new Profile();

        return this.profileRepository.save(profile);
    }

    @Transactional
    public Profile updateProfile(Long id, UpdateProfileRequest request) {
        Profile profile = this.profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile was not found."));
        profile.setCity(request.getCity());
        profile.setEmail(request.getEmail());
        profile.setState(request.getState());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setCountry(request.getCountry());
        profile.setResume(request.getResume());

        return this.profileRepository.save(profile);

    }
}
