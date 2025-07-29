package com.automobilebavaria.backend.dto;

public record CreateAdRequest(
        String title,
        String brand,
        String model,
        String type,
        int doors,
        int fuelConsumption,
        int year,
        int mileage,
        int price,
        String description
) {
}
