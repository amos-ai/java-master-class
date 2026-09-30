package com.amos.car;

import java.util.UUID;

public class CarService {
    private CarDao carDao;
    private Car[] electric;


    public CarService(CarDao carDao) {
        this.carDao = carDao;
        this.electric = electric;
    }


    public Car getCarById(UUID uuid) {
        return carDao.findCarById(uuid);
    }

    public Car [] getAllCars() {
        return carDao.findAllCars();
    }

    public Car[] getElectricCars() {

        Car[] cars = carDao.findAllCars();
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
