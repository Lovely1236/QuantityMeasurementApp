package com.example.quantity_measurement_app.controller;
import com.example.quantity_measurement_app.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> req) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.get("username"),
                        req.get("password")
                )
        );

        return provider.generateToken(req.get("username"));
    }

    @GetMapping("/oauth-success")
    public String success() {
        return "OAuth Login Successful";
    }
}