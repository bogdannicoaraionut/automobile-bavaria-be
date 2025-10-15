package com.automobilebavaria.backend.service;

import com.automobilebavaria.backend.dto.CarMakerDTO;
import com.automobilebavaria.backend.dto.CarModelDTO;
import com.automobilebavaria.backend.entity.Car;
import com.automobilebavaria.backend.mapper.CarMapper;
import com.automobilebavaria.backend.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @NonNull
    public List<CarMakerDTO> getCarMakers() {
        List<Car> cars = carRepository.findDistinctMakers();

        return carMapper.toCarMakerDTOs(cars);
    }

    @NonNull
    public List<CarModelDTO> getCarMakerModels(@NonNull String maker) {
        List<Car> cars = carRepository.findDistinctModelsByMaker(maker);

        return carMapper.toCarModelDTOs(cars);
    }
}
