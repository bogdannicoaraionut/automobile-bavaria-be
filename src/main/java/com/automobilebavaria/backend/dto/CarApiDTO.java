package com.automobilebavaria.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarApiDTO(
        Long id,
        String car,
        @JsonProperty("car_model") String carModel,
        @JsonProperty("car_color") String carColor,
        @JsonProperty("car_model_year") int carModelYear,
        @JsonProperty("car_vin") String carVin,
        String price,
        boolean availability
) {}
