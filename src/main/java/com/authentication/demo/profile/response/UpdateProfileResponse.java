package com.authentication.demo.profile.response;

import com.authentication.demo.profile.Profile;

public class UpdateProfileResponse {

    private Profile profile;

    public UpdateProfileResponse() {

    }

    public UpdateProfileResponse(Profile profile) {
        this.profile = profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
}
