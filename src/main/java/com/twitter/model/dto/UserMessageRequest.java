package com.twitter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserMessageRequest {
    private final String email;
    private final String message;

    public UserMessageRequest(@Email @JsonProperty("email") String email,
                              @Size (max = 140) @JsonProperty("message") String message) {
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