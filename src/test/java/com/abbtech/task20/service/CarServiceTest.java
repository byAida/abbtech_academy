package com.abbtech.task20.service;

import com.abbtech.task20.model.Car;
import com.abbtech.task20.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void saveCarTest() {
        Car car = new Car(1L, "BMW", "X5");

        when(carRepository.save(car)).thenReturn(car);

        Car result = carService.save(car);

        assertEquals("BMW", result.getBrand());
    }

    @Test
    void getCarByIdTest() {
        Car car = new Car(1L, "Audi", "A6");

        when(carRepository.findById(1L))
                .thenReturn(Optional.of(car));

        Car result = carService.getById(1L);

        assertEquals("Audi", result.getBrand());
    }

    @Test
    void getAllCarsTest() {
        List<Car> cars = List.of(
                new Car(1L, "BMW", "X5"),
                new Car(2L, "Mercedes", "E200")
        );

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = carService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void updateCarTest() {
        Car updatedCar = new Car(1L, "Toyota", "Camry");

        when(carRepository.update(1L, updatedCar))
                .thenReturn(updatedCar);

        Car result = carService.update(1L, updatedCar);

        assertEquals("Toyota", result.getBrand());
    }
}

