package com.example.springecommerce.dto;

import com.example.springecommerce.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationDTO {

    @NotBlank(message = "Name is mandotary")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandotary")
    private String email;
    @NotBlank(message = "password is mandotary")
    private String password;
    private Role role;

}
