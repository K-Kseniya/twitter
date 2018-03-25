package com.twitter.model.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
public class PostForm {
    @Email
    private String email;
    @Size(max = 140)
    private String post;
}