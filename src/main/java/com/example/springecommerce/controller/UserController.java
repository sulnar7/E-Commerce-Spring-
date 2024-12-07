package com.example.springecommerce.controller;

import com.example.springecommerce.dto.RegistrationDTO;
import com.example.springecommerce.dto.UserLoginDto;
import com.example.springecommerce.service.ProductService;
import com.example.springecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@lombok.extern.log4j.Log4j2
@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/register")
    public RegistrationDTO register(@Valid @RequestBody RegistrationDTO registrationDTO) {

        RegistrationDTO registeredUser = userService.registerUser(registrationDTO);
        log.info(registeredUser+"registered user");
        return registeredUser;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDto user) {
//        log.info("bura isleyir");
        return userService.authenticate(user.getEmail(), user.getPassword());

    }


    @GetMapping("/ibdex")
    public String ibdex() {
        return "Hello World";
    }

}
