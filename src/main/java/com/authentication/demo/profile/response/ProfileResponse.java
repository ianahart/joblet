package com.authentication.demo.profile.response;

import com.authentication.demo.profile.Profile;

public class ProfileResponse {

    private Profile profile;

    public ProfileResponse() {

    }

    public ProfileResponse(Profile profile) {
        this.profile = profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
}
