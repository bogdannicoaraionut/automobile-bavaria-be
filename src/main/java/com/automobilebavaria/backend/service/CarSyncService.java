package com.automobilebavaria.backend.service;

import com.automobilebavaria.backend.dto.CarApiDTO;
import com.automobilebavaria.backend.dto.CarApiResponse;
import com.automobilebavaria.backend.entity.Car;
import com.automobilebavaria.backend.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
@AllArgsConstructor
public class CarSyncService {

    private final CarRepository carRepository;
    private final WebClient webClient;

    public void syncCars() {
        CarApiResponse response = webClient.get()
                .uri("/api/cars/")
                .retrieve()
                .bodyToMono(CarApiResponse.class)
                .block();

            if (response != null) {

                carRepository.deleteAll();

                for (CarApiDTO dto : response.cars()) {
                    Car car = new Car();
                    car.setMaker(dto.car());
                    car.setModel(dto.carModel());
                    car.setYearModel(dto.carModelYear());
                    car.setPrice(dto.price().replace("$", "").replace(",", ""));
                    carRepository.save(car);
                }
            }
    }
}
