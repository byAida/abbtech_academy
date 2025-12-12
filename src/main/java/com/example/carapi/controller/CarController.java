package com.example.carapi.controller;
import com.example.carapi.model.Car;
import com.example.carapi.service.CarService;
import java.util.List;

public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // əlavə etmək
    public void addCar(int id, String color, int speed) {
        Car car = new Car(id, color, speed);
        carService.addCar(car);
    }

    // silmək
    public boolean deleteCarById(int id) {
        return carService.deleteCarById(id);
    }

    // yeniləmək
    public boolean updateCar(int id, String color, int speed) {
        Car updatedCar = new Car(id, color, speed);
        return carService.updateCar(id, updatedCar);
    }

    // Bütün avtomobilləri göstərmək
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
}