package com.automobilebavaria.backend.controller;

import com.automobilebavaria.backend.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private CarService carService;

    @GetMapping("/cars-manufacturers")
    public ResponseEntity<Void> syncCarManufacturers() {
        carService.getCarMakers();

        return ResponseEntity.ok().build();
    }
}
