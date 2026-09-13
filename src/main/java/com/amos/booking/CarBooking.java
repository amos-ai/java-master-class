package com.amos.booking;

import com.amos.car.Car;
import com.amos.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class CarBooking {
    private UUID uuid;
    private User user;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;

    // Constructors

    public CarBooking(UUID uuid, User user, Car car,
                      LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.uuid = uuid;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }


    // getters


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // hashCode


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarBooking that = (CarBooking) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(user, that.user) && Objects.equals(car, that.car) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, user, car, startDate, endDate, price);
    }

    // toString

    @Override
    public String toString() {
        return "CarBooking{" +
                "uuid=" + uuid +
                ", user=" + user +
                ", car=" + car +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}