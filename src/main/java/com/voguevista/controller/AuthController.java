package com.voguevista.controller;
import org.springframework.web.bind.annotation.RestController;

import com.voguevista.service.AuthService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.voguevista.dto.RegisterRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import  com.voguevista.dto.LoginRequest;
import com.voguevista.repository.UserRepository;
import com.voguevista.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")  // allow html to call this api from any domain
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
        String result = authService.register(request);
        if(result.equals("User registered successfully")){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    User user = authService.login(request);
    if (user != null) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("username", user.getUsername());
        response.put("userId", user.getId());
        response.put("role", user.getRole());
        return ResponseEntity.ok(response);
    }
    // ✅ Return JSON for error too — not plain text!
    Map<String, Object> error = new HashMap<>();
    error.put("message", "Invalid username or password");
    return ResponseEntity.status(401).body(error);
}
}
    
    

