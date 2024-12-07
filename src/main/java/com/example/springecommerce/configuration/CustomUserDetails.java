package com.example.springecommerce.configuration;

import com.example.springecommerce.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails extends User {

    public CustomUserDetails(String username, String password, List<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
