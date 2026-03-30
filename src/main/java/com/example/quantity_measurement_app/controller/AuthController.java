package com.example.quantity_measurement_app.controller;
import com.example.quantity_measurement_app.entity.User;
import com.example.quantity_measurement_app.repository.UserRepository;
import com.example.quantity_measurement_app.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider provider;
    private final AuthenticationManager authManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> req) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.get("email"),
                        req.get("password")
                )
        );

        return provider.generateToken(req.get("email"));
    }

    @GetMapping("/oauth-success")
    public String success() {
        return "OAuth Login Successful";
    }
    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> req) {

        if (userRepository.findByEmail(req.get("email")).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(req.get("name"));
        user.setEmail(req.get("email"));
        user.setPassword(passwordEncoder.encode(req.get("password")));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User registered successfully";
    }
}