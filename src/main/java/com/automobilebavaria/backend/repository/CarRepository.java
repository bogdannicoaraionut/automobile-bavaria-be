package com.automobilebavaria.backend.repository;

import com.automobilebavaria.backend.entity.Ad;
import com.automobilebavaria.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT DISTINCT c.maker FROM Car c")
    List<String> findDistinctMakers();

    @Query("SELECT DISTINCT c.model FROM Car c WHERE LOWER(c.maker) = LOWER(:maker)")
    List<String> findDistinctModelsByMaker(String maker);
}
