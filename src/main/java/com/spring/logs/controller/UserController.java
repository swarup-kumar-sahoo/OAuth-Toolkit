package com.spring.logs.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user/profile")
    public Map<String, Object> getProfile(
            Authentication authentication
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "email",
                authentication.getName()
        );

        response.put(
                "authorities",
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );

        response.put(
                "message",
                "Welcome User"
        );

        return response;
    }

    @GetMapping("/user/me")
    public Map<String, Object> currentUser(
            Authentication authentication
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "username",
                authentication.getName()
        );

        response.put(
                "roles",
                authentication.getAuthorities()
        );

        return response;
    }

    @GetMapping("/admin/dashboard")
    public Map<String, String> adminDashboard() {

        Map<String, String> response =
                new HashMap<>();

        response.put(
                "message",
                "Welcome Admin"
        );

        return response;
    }
}