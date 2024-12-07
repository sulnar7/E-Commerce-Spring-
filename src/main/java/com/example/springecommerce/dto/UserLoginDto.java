package com.example.springecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandotary")
    private String email;
    @NotBlank(message = "Password is mandotary")
    private String password;
}
