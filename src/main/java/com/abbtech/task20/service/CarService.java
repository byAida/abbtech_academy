package com.abbtech.task20.service;

import com.abbtech.task20.model.Car;
import com.abbtech.task20.repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car getById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Belə avtomobil tapılmadı"));
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car update(Long id, Car car) {
        return carRepository.update(id, car);
    }
}
