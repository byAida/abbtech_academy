package com.example.carapi.model;

public class Car {
    private int id;
    private String color;
    private int speed;

    public Car(int id, String color, int speed) {
        this.id = id;
        this.color = color;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color='" + color +
                ", speed=" + speed +
                '}';
    }
}
