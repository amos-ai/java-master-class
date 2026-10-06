package com.amos.car;

import java.util.UUID;

public class CarService {

    private final CarDAO carDAO;
    private Car[] electric;


    public CarService(CarDAO carDAO, Car[] electric) {
        this.carDAO = carDAO;
        this.electric = electric;
    }



    public Car getCarById(UUID uuid) {
        return carDAO.findCarById(uuid);
    }

    public Car [] getAllCars() {
        return carDAO.getCars();
    }

    public Car[] getElectricCars() {

        Car[] cars = carDAO.getCars();
        int countElectric = 0;

        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                countElectric++;
            }
        }
        Car[] electric = new Car[countElectric];
        int index = 0;

        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                electric[index] = car;
                index++;
            }
        }
        return  electric;
    }
}
