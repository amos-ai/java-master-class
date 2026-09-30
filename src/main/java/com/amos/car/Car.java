package com.amos.car;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

// Data model -> This is BMW modelx €70/day

public class Car {
    private UUID uuid;
    private String regNumber;
    private BigDecimal rentalPricePerDay;
    private Brand brand;
    boolean electric;

    // Constructors
    public Car(UUID uuid, String regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric) {
        this.uuid = uuid;
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.electric = isElectric;

    }

    // getters & setter

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        Car.this.electric = electric;
    }


    // hashCode


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return electric == car.electric && Objects.equals(uuid, car.uuid) && Objects.equals(regNumber, car.regNumber) && Objects.equals(rentalPricePerDay, car.rentalPricePerDay) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, regNumber, rentalPricePerDay, brand, electric);
    }

    // toString

    @Override
    public String toString() {
            return brand + " " +
                    " - €" + rentalPricePerDay + "/day" +
                    (electric ? " [Electric]" : "");
    }
}
