package com.spring.logs.controller;

import com.spring.logs.dto.AuthResponse;
import com.spring.logs.dto.CreateAdminRequest;
import com.spring.logs.dto.LoginRequest;
import com.spring.logs.dto.RegisterRequest;
import com.spring.logs.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdmin(
            @RequestBody CreateAdminRequest request
    ) {
        return ResponseEntity.ok(
                authService.createAdmin(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}