package com.example.quantity_measurement_app.security.jwt;

import com.example.quantity_measurement_app.security.user.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider provider;
    private final CustomUserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");


        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);



            try {
                String username = provider.getUsername(token);
                System.out.println("USERNAME FROM TOKEN: " + username);

                if (username != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = service.loadUserByUsername(username);
                    System.out.println("USER FOUND: " + userDetails.getUsername());

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("TOKEN: " + token);
                    System.out.println("USERNAME FROM TOKEN: " + username);

                }

            } catch (Exception e) {
                System.out.println("JWT ERROR: " + e.getMessage());
            }

        }

        chain.doFilter(request, response);
    }
}