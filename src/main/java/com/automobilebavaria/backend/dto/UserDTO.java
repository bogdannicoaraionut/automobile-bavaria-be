package com.automobilebavaria.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String username,
        String address
) {
}
