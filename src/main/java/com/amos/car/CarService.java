package com.amos.car;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarService {

    private final CarDAO carDAO;


    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }



    public Car getCarById(UUID uuid) {
        return carDAO.findCarById(uuid);
    }

    public List<Car> getAllCars() {
        return carDAO.getCars();
    }

    /**
    public List<Car> getElectricCars() {

        List<Car> cars = carDAO.getCars();
        int countElectric = 0;

        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                countElectric++;
            }
        }
        List<Car> electric = new ArrayList<>();
        int index = 0;

        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                electric.set(index, car);
                index++;
            }
        }
        return  electric;
    }
     **/
}
