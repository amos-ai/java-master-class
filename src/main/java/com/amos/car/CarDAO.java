package com.amos.car;

import java.util.List;
import java.util.UUID;

public interface CarDAO {
    List<Car> getCars();
    Car findCarById(UUID carId);
}
