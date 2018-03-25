package com.twitter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowRequest {
    private final String userEmail;
    private final String friendEmail;

    public FollowRequest(@JsonProperty("userEmail") String userEmail, @JsonProperty("friendEmail") String friendEmail) {
        this.userEmail = userEmail;
        this.friendEmail = friendEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFriendEmail() {
        return friendEmail;
    }
}