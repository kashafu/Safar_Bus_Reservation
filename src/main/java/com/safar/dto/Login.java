package com.safar.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Login {

    @Email
    private String email;

    @NotNull(message="Password can not be null!")
    @NotBlank(message= "Password can not be blank!")
    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters including alphanumerics and special characters")
    private String password;
}
