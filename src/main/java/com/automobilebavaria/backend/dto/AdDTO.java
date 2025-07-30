package com.automobilebavaria.backend.dto;

import java.time.Instant;

public record AdDTO(
        Long id,
        String title,
        String brand,
        String model,
        String type,
        int doors,
        int fuelConsumption,
        int year,
        int mileage,
        int price,
        String description,
        Instant createdAt,
        int userId
) {
}
