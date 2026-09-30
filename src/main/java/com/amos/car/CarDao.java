package com.amos.car;

// Responsible for interacting with the Car Database to find Car by ID

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {
    private static final Car[] cars;

    static {
        cars = new Car[] {

        new Car( UUID.randomUUID(), "TY5", new BigDecimal("50.00"), Brand.TOYOTA, false),
        new Car(UUID.randomUUID(), "AUQ3", new BigDecimal("70.00"), Brand.AUDI, true),
        new Car(UUID.randomUUID(), "BZ2", new BigDecimal("80.00"), Brand.MERCEDES, false),
        new Car(UUID.randomUUID(), "TS1", new BigDecimal("95.00"), Brand.TESLA, true)
        };
    }


    public Car findCarById(UUID uuid) {

        for (Car car : cars) {
            if (car != null && car.getUuid().equals(uuid)) {
                return car;
            }
        }
        return null;
        }

    public Car[] findAllCars(){
        return cars;
    }
}
