package com.example.carapi.controller;

import com.example.carapi.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarControllerTest {

    private CarService carService;
    private CarController carController;

    @BeforeEach
    void setUp() {
        carService = new CarService();
        carController = new CarController(carService);
    }

    @Test
    void testAddCar() {
        carController.addCar(1, "Qırmızı", 120);
        assertEquals(1, carService.getAllCars().size());
    }

    @Test
    void testDeleteCarById() {
        carController.addCar(1, "Qırmızı", 120);
        boolean isDeleted = carController.deleteCarById(1);
        assertTrue(isDeleted);
        assertEquals(0, carService.getAllCars().size());
    }

    @Test
    void testUpdateCar() {
        carController.addCar(1, "Qırmızı", 120);
        boolean isUpdated = carController.updateCar(1, "Göy", 150);
        assertTrue(isUpdated);
        assertEquals("Yaşıl", carService.getAllCars().get(0).getColor());
        assertEquals(150, carService.getAllCars().get(0).getSpeed());
    }
}
