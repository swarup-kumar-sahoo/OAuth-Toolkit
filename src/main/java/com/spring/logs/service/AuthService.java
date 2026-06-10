package com.spring.logs.service;

import com.spring.logs.dto.AuthResponse;
import com.spring.logs.dto.CreateAdminRequest;
import com.spring.logs.dto.LoginRequest;
import com.spring.logs.dto.RegisterRequest;
import com.spring.logs.model.User;
import com.spring.logs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    @Value("${admin.creation.pin}")
    private String adminPin;

    public String createAdmin(CreateAdminRequest request) {

        if (!adminPin.equals(request.getPin())) {
            throw new RuntimeException("Invalid Admin Pin");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User admin = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .provider("LOCAL")
                .role("ROLE_ADMIN")
                .build();

        userRepository.save(admin);

        return "Admin Created Successfully";
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .provider("LOCAL")
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        request.getEmail()
                );

        String token =
                jwtService.generateToken(userDetails);

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole()
        );
    }
}