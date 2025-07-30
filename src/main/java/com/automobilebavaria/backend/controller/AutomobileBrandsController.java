package com.automobilebavaria.backend.controller;

import com.automobilebavaria.backend.dto.CarApiDTO;
import com.automobilebavaria.backend.service.CarService;
import com.automobilebavaria.backend.service.CarSyncService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class AutomobileBrandsController {

    private final CarSyncService carSyncService;
    private final CarService carService;

    @PostMapping("/sync")
    public ResponseEntity<String> sync() {
        carSyncService.syncCars();
        return ResponseEntity.ok("Car sync completed.");
    }

    @GetMapping("/makers")
    public ResponseEntity<List<String>> getAllMakers() {
        return ResponseEntity.ok().body(carService.getCarMakers());
    }

    @GetMapping("/makers/{maker}/models")
    public ResponseEntity<List<String>> getAllMakerModels(@PathVariable String maker) {
        return ResponseEntity.ok().body(carService.getCarMakerModels(maker));
    }
}
