package com.spring.logs.dto;

import lombok.Data;

@Data
public class CreateAdminRequest {

    private String pin;

    private String name;

    private String email;

    private String password;
}