package com.example.springecommerce.service;

import com.example.springecommerce.dto.RegistrationDTO;
import com.example.springecommerce.model.User;

public interface UserService {
    RegistrationDTO registerUser(RegistrationDTO registrationDTO);

    String authenticate(String email, String password);

    User getUserByEmail(String email);
}
