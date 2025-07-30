package com.automobilebavaria.backend.dto;

import java.time.Instant;

public record CarDTO(
        Long id,
        String maker,
        String model
) {
}
