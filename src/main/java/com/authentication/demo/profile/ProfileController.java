package com.authentication.demo.profile;

import java.net.URL;
import java.util.Map;

import com.authentication.demo.profile.request.UpdateProfileRequest;
import com.authentication.demo.profile.request.UploadPDFRequest;
import com.authentication.demo.profile.response.ProfileResponse;
import com.authentication.demo.profile.response.UpdateProfileResponse;
import com.authentication.demo.profile.response.UploadPDFResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping(path = "/api/v1/profiles")
public class ProfileController {

    @Autowired
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProfileResponse(this.profileService.getProfile(id)));
    }

    @PatchMapping("/upload/{id}")
    public ResponseEntity<UploadPDFResponse> upload(@PathVariable("id") Long id, UploadPDFRequest request) {
        String newFileName = this.profileService.uploadResume(request, id);
        Map<String, String> data = this.profileService.downloadPublicUrlResume(newFileName, id);
        this.profileService.deleteOldResume(id);
        this.profileService.updateResume(data.get("url"), id, data.get("fileName"));

        return ResponseEntity.status(HttpStatus.OK).body(new UploadPDFResponse(data.get("url")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateProfileResponse> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            @PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UpdateProfileResponse(this.profileService.updateProfile(id, request)));
    }
}
