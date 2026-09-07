package com.amos.car;

import java.util.UUID;

public class CarService {
    private CarDao carDao;
    private Car[] electric;

    /**
    - Is it electric? -- getElectricCars
    - What brand is it?
    - What model?
    - What's the price? -- getAllPrice
    - Find car by ID? -- findCarById
    - List all cars? -- getAllCars
     **/
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
        /**  ELECTRIC CARS
        - find all cars and countElectric
        -  check if car isElectric
         - if true, add electric to count
        **/
        try {

        Car[] cars = carDao.findAllCars();
        int countElectric = 0;

        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                countElectric++;
                Car[] electric = new Car[countElectric];
                System.out.println(electric);
            }
        }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return  electric;
    }
}
