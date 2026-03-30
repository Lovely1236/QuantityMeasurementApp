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

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        final String finalName = oAuth2User.getAttribute("name") != null
                ? oAuth2User.getAttribute("name")
                : email;

        User user = repository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(finalName);
                    newUser.setRole("ROLE_USER");
                    return repository.save(newUser);
                });

        String token = provider.generateToken(user.getEmail());

        String redirectUrl = "http://localhost:5173/oauth-success?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}