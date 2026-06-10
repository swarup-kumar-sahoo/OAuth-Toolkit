package com.spring.logs.config;

import com.spring.logs.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler
        implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.core.Authentication authentication
    ) throws IOException, ServletException {

        String email =
                authentication.getName();

        UserDetails userDetails =
                User.withUsername(email)
                        .password("")
                        .authorities("ROLE_USER")
                        .build();

        String token =
                jwtService.generateToken(userDetails);

        response.sendRedirect(
                "http://localhost:3000/oauth-success?token="
                        + token
        );
    }
}