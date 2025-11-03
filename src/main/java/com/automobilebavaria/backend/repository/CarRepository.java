package com.automobilebavaria.backend.repository;

import com.automobilebavaria.backend.entity.Ad;
import com.automobilebavaria.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT DISTINCT c FROM Car c")
    List<Car> findDistinctMakers();

    @Query(value = """
      SELECT * FROM car c
      WHERE LOWER(c.maker) = LOWER(:maker)
      AND c.id IN (
          SELECT MIN(id)
          FROM car
          WHERE LOWER(maker) = LOWER(:maker)
          GROUP BY model
      )
      """, nativeQuery = true)
    List<Car> findDistinctModelsByMaker(String maker);
}
