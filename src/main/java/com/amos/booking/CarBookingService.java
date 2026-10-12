package com.amos.booking;

import com.amos.car.Car;
import com.amos.car.CarService;
import com.amos.user.User;
import com.amos.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


public class CarBookingService {

    private CarBookingDAO carBookingDAO;
    private UserService userService;
    private CarService carService;

    public CarBookingService(CarBookingDAO carBookingArrayDataAccessService, UserService userService, CarService carService) {
        this.carBookingDAO = carBookingArrayDataAccessService;
        this.userService = userService;
        this.carService = carService;
    }

    /** === 1.BOOK A CAR === **/


    public CarBooking bookCar(UUID userId,
                              UUID carId,
                              LocalDate startDate,
                              LocalDate endDate
    ) {
        UUID bookingId = UUID.randomUUID();


        User user =  userService.getUserId(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Car car = carService.getCarById(carId);
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Date must not be empty");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Start date must come before end date");
        }

        long rentalDays = ChronoUnit.DAYS.between(startDate, endDate);

        BigDecimal totalrentalDays =
                BigDecimal.valueOf(rentalDays).multiply(car.getRentalPricePerDay());


        CarBooking booking = new CarBooking(
                bookingId,
                user,
                car,
                startDate,
                endDate,
                totalrentalDays
        );

        if (carBookingDAO.carIsBooked(carId)) {
            throw new IllegalArgumentException("Car is already booked");
        }

        carBookingDAO.saveBooking(booking);
        return booking;


    }


    /** == 2. DELETE BOOKING == **/
    public  void deleteBooking(UUID bookingId) {


        if (bookingId == null) {
            throw new IllegalArgumentException("booking not found");
        }
        carBookingDAO.findBookingById(bookingId);

        carBookingDAO.deleteBooking(bookingId);

    }
    /** === 3. VIEW USER BOOKING **/

    public CarBooking[] getUserBooking(UUID userId) {
        CarBooking[] allBookings = carBookingDAO.getBookings();
        int count = 0;

        for (CarBooking booking : allBookings) {
            if (booking != null && booking.getUser().getUuid().equals(userId) ){
                count++;
            }
        }
        CarBooking[] userBookings = new CarBooking[count];
        int index = 0;

        for (CarBooking booking : allBookings) {
            if (booking != null && booking.getUser().getUuid().equals(userId)) {
                userBookings[index++] = booking;
            }
        }
        return userBookings;
    }

    /** === 5. GET AVAILABLE CARS === **/
    public Car[] getAvailableCars() {
        Car[] allCars = carService.getAllCars();
        Car[] temp = new Car[allCars.length];

        int count = 0;
        for (Car car : allCars) {
            if (car != null && !carBookingDAO.carIsBooked(car.getUuid())) {
                temp[count] = car;
                count++;
            }
        }
        Car[] availableCars = new Car[count];

        for (int i = 0; i < count ; i++) {
            availableCars[i] = temp[i];
        }
        return availableCars;
    }

    /** === GET ELECTRIC CARS**/
    public Car[] getAvailableElectricCars() {
        Car[] availableCars = getAvailableCars();
        Car[] temp = new Car[availableCars.length];
        int count = 0;

        for (Car car : availableCars) {
            if (car.isElectric()) {
                temp[count] = car;
                count++;
            }
        }

        Car[] electricCars = new Car[count];

        for (int i = 0; i < count ; i++) {
            electricCars[i] = temp[i];
        }
        return electricCars;
    }


    /** === VIEW ALL BOOKINGS === **/
    public CarBooking[] getAllBookings() {
        return carBookingDAO.getBookings();
    }

}

