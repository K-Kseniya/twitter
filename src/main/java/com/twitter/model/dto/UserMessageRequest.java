package com.twitter.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserMessageRequest {
    private final String email;
    private final String message;

    public UserMessageRequest(@Email String email, @Size(max = 140) String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}