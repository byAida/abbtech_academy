package com.abbtech.task20.repository;

import com.abbtech.task20.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Car save(Car car);

    Optional<Car> findById(Long id);

    List<Car> findAll();

    Car update(Long id, Car car);
}

