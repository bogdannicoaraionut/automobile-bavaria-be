package com.automobilebavaria.backend.dto;

public record JwtLoginResponse(
        String token,
        String username
) {
}
