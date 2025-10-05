package com.example.auth.controller;

import com.example.auth.dto.AuthRequest;
import com.example.auth.dto.AuthResponse;
import com.example.auth.entity.AppUser;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        if (userService.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("username already exists");
        }
        AppUser created = userService.createUser(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(created.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
            String token = jwtUtil.generateToken(req.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid username/password");
        }
    }
}

