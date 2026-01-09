package com.abbtech.task20.repository;

import com.abbtech.task20.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final List<Car> cars = new ArrayList<>();

    @Override
    public Car save(Car car) {
        cars.add(car);
        return car;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public Car update(Long id, Car car) {
        Car existingCar = findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());

        return existingCar;
    }
}
