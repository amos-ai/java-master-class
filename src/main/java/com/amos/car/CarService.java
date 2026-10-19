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

}
