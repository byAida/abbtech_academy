package com.example.carapi.service;

import com.example.carapi.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
    }

    @Test
    void testAddCar() {
        Car car = new Car(1, "Qırmızı", 120);
        carService.addCar(car);
        assertEquals(1, carService.getAllCars().size());
    }
        @Test
        void testDeleteCarById () {
            Car car = new Car(1, "Qırmızı", 120);
            carService.addCar(car);
            boolean isDeleted = carService.deleteCarById(1);
            assertTrue(isDeleted);
            assertEquals(0, carService.getAllCars().size());
        }

        @Test
        void testUpdateCar () {
            Car car = new Car(1, "Qırmızı", 120);
            carService.addCar(car);
            Car updatedCar = new Car(1, "Göy", 150);
            boolean isUpdated = carService.updateCar(1, updatedCar);
            assertTrue(isUpdated);
            assertEquals("Göy", carService.getAllCars().get(0).getColor());
            assertEquals(150, carService.getAllCars().get(0).getSpeed());
        }
    }