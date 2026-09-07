package com.amos.car;

// Responsible for interacting with the Car Database to find Car by ID

import java.util.UUID;

public class CarDao {
    // My database = Arrays of cars
    // get all cars --> for each car in car array return all cars
    // add a car
    // find car by id
    // return null
/*
    private static Car[] cars;

    static {
        cars = new Car[5];
    }

    public void findCarBrand (String brand) {
        Brand carBrands = new Brand();
        for (brand : Brand )
    }

    public void addCars(Car car) {
        for (int i = 0; i < cars.length; i++) {
            System.out.println(i);
        }
    }
    */

    // CarDoa will access and store cars
    // will find cars by Id


        private Car[] cars;

    public CarDao(Car[] cars) {
        this.cars = cars;
    }


    public Car findCarById(UUID uuid) {

        for (Car car : cars) {
            if (car != null && car.getUuid().equals(uuid)) {
                return car;
            }
        }
        return null;
        }
        //find cars by brand
    // for all the cars in store
    // view their brands
    // if user choice is equal to brand
    // return that car and brand.
    public Brand findCarByBrand( Brand brand) {
        for (Car car : cars) {
            if (car.getBrand().equals(brand)) {
                return brand;
            }
        }
        return null;
    }

    public Car[] findAllCars(){
        return cars;
    }
}
