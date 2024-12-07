package com.example.springecommerce.service;

import com.example.springecommerce.dto.RegistrationDTO;
import com.example.springecommerce.exception.UserAlreadyExsitsException;
import com.example.springecommerce.mapper.UserMapper;
import com.example.springecommerce.model.User;
import com.example.springecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper mapper;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;

    @Override
    public RegistrationDTO registerUser(RegistrationDTO registrationDTO) {
        // Check if user already exists
        log.info("Registering user: {}", registrationDTO);
        Optional<RegistrationDTO> existingUser = userRepository.findByEmail(registrationDTO.getEmail()).
                map(mapper::toUserRegistrationDto);
        if (existingUser.isPresent()) {
            log.info("User already exists: {}", existingUser.get().getEmail());
            throw new UserAlreadyExsitsException("User already exists with this email");
        }
        // Encode the password
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        // Set default role
        User user = userMapper.toUser(registrationDTO);
        return userMapper.toUserRegistrationDto(userRepository.save(user));
    }

    @Override
    public String authenticate(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (existingUser.isPresent()) {

            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities()
                        .stream()
                        .map(authority -> authority.getAuthority())
                        .toList());
            } else {
                throw new RuntimeException("Wrong password");
            }

        } else {
            throw new RuntimeException("User not found with this email" + email);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
