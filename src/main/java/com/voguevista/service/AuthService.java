package com.voguevista.service;

import com.voguevista.dto.LoginRequest;
import com.voguevista.dto.RegisterRequest;
import com.voguevista.entity.User;
import com.voguevista.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already taken";
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt hash
        user.setEmail(request.getEmail());
        user.setPlace(request.getPlace());
        user.setRole("user");

        userRepository.save(user);
        return "User registered successfully";
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) return null;

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }
}