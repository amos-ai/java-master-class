package com.amos.car;

// Responsible for interacting with the Car Database to find Car by ID

import com.amos.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class CarArrayDataAccessService implements CarDAO{
    private static final List<Car> cars;

    static {

        cars = new ArrayList<>();
            cars.add(new Car( UUID.randomUUID(), "TY5", new BigDecimal("50.00"), Brand.TOYOTA, false));
            cars.add(new Car(UUID.randomUUID(), "AUQ3", new BigDecimal("70.00"), Brand.AUDI, true));
            cars.add(new Car(UUID.randomUUID(), "BZ2", new BigDecimal("80.00"), Brand.MERCEDES, false));
            cars.add(new Car(UUID.randomUUID(), "TS1", new BigDecimal("95.00"), Brand.TESLA, true));
    }

    @Override
    public List<Car> getCars() {

        return new ArrayList<> (cars);
    }

    @Override
    public Car findCarById(UUID carId) {
        for (Car car : getCars()) {
            if (car != null && car.getUuid().equals(carId)) {
                return car;
            }
        }
        return null;
    }
}
