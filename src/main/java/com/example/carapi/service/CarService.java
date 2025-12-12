package com.example.carapi.service;
import com.example.carapi.model.Car;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    private List<Car> cars = new ArrayList<>();

    // Avtomobil əlavə etmək üçün
    public void addCar(Car car) {
        cars.add(car);
    }

    // Avtomobil silmək
    public boolean deleteCarById(int id) {
        return cars.removeIf(car -> car.getId() == id);
    }

    // Avtomobili yeniləməmək
    public boolean updateCar(int id, Car updatedCar) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setColor(updatedCar.getColor());
                car.setSpeed(updatedCar.getSpeed());
                return true;
            }
        }
        return false;
    }

    // Bütün avtomobilləri əldə etmək
    public List<Car> getAllCars() {
        return cars;
    }
}