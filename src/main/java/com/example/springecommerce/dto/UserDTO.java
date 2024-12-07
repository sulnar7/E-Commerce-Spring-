package com.example.springecommerce.dto;

import com.example.springecommerce.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandotary")
    private String email;
    private String name;
    private Role role;
    private String password;

}
