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
            cars.add(new Car( UUID.fromString("195bd68a-bd41-4827-b554-1dd2cf454deb"), "TY5", new BigDecimal("50.00"), Brand.TOYOTA, false));
            cars.add(new Car(UUID.fromString("37bca99a-e182-4ada-a306-45ec9863cc4b"), "AUQ3", new BigDecimal("70.00"), Brand.AUDI, true));
            cars.add(new Car(UUID.fromString("42a22a8e-80eb-45a8-aad0-f7d74281df88"), "BZ2", new BigDecimal("80.00"), Brand.MERCEDES, false));
            cars.add(new Car(UUID.fromString("a5a30f3b-49b0-4744-8a7e-8559c20c150c"), "TS1", new BigDecimal("95.00"), Brand.TESLA, true));
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
