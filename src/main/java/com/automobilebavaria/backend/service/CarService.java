package com.automobilebavaria.backend.service;

import com.automobilebavaria.backend.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @NonNull
    public List<String> getCarMakers() {
        return carRepository.findDistinctMakers();
    }

    @NonNull
    public List<String> getCarMakerModels(@NonNull String maker) {
        return carRepository.findDistinctModelsByMaker(maker);
    };
}
