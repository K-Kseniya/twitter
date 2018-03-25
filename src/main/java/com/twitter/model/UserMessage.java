package com.twitter.model;


import com.twitter.model.dto.UserMessageRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserMessage implements Comparable {
    private final String uid;
    private final String userEmail;
    private final String message;
    private final LocalDateTime timestamp;

    public UserMessage(UserMessageRequest post) {
        this.uid = UUID.randomUUID().toString();
        this.userEmail = post.getEmail();
        this.message = post.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public String getUid() {
        return uid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // TODO NLE check
    @Override
    public int compareTo(Object o) {
        UserMessage another = (UserMessage) o;
        return this.getTimestamp().compareTo(another.getTimestamp());
    }
}