package com.automobilebavaria.backend.dto;

import java.util.List;

public record CarApiResponse(
    List<CarApiDTO> cars
) {}
