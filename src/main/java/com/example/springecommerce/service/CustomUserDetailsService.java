package com.example.springecommerce.service;

// src/main/java/com/yourpackage/service/impl/CustomUserDetailsService.java


import com.example.springecommerce.configuration.CustomUserDetails;
import com.example.springecommerce.model.Role;
import com.example.springecommerce.model.User;
import com.example.springecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from the database
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()));
        // Return custom user details with roles
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        log.info(new SimpleGrantedAuthority(user.getRole().name())+"roleee");
        return new CustomUserDetails(user.getEmail(), user.getPassword(),authorities);
    }
}
