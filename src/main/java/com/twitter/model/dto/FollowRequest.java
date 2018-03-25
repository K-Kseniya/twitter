package com.twitter.model.dto;

public class FollowRequest {
    private final String userEmail;
    private final String friendEmail;

    public FollowRequest(String userEmail, String friendEmail) {
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
