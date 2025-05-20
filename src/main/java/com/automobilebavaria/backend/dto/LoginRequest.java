package com.automobilebavaria.backend.dto;

public record LoginRequest(
        String userName,
        String password
) {
}
