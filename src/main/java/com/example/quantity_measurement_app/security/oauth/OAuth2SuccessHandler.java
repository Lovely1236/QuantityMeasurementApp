package com.example.quantity_measurement_app.security.oauth;

import com.example.quantity_measurement_app.entity.User;
import com.example.quantity_measurement_app.repository.UserRepository;
import com.example.quantity_measurement_app.security.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository repository;
    private final JwtTokenProvider provider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");


        // 🔥 SAVE USER IF NOT EXISTS
        User user = repository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setUsername(email);
                    newUser.setRole("USER");
                    return repository.save(newUser);
                });

        // 🔥 GENERATE JWT
        String token = provider.generateToken(user.getEmail());

        // 🔥 RETURN TOKEN
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");

    }
}