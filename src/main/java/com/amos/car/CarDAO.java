package com.amos.car;

import java.util.UUID;

public interface CarDAO {
    Car[] getCars();
    Car findCarById(UUID carId);
}
