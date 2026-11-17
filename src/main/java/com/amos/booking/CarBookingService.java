package com.amos.booking;

import com.amos.car.Car;
import com.amos.car.CarService;
import com.amos.user.User;
import com.amos.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


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
            throw new IllegalArgumentException("booking ID not found");
        }
        carBookingDAO.deleteBooking(bookingId);

    }
    /** === 3. VIEW USER BOOKING **/

    public List<CarBooking> getUserBooking(UUID userId) {

        List<CarBooking> userBookings = new ArrayList<>();

        List<CarBooking> allBookings = carBookingDAO.getBookings();

        return allBookings.stream()
                .filter(Objects::nonNull)
                .filter(booking -> booking.getUser() != null)
                .filter(booking -> userId.equals(booking.getUser().getUuid()))
                .collect(Collectors.toList());
    }

    /** === 5. GET AVAILABLE CARS === **/
    public List<Car> getAvailableCars() {
        List<Car> allCars = carService.getAllCars();
        List<Car> availableCars = new ArrayList<>();

        return allCars.stream()
                .filter(Objects::nonNull)
                .filter(car -> !carBookingDAO.carIsBooked(car.getUuid()))
                .collect(Collectors.toList());
    }

    /** === GET ELECTRIC CARS**/
    public List<Car> getAvailableElectricCars() {
        List<Car> electricCars = new ArrayList<>();




        return getAvailableCars().stream()
                .filter(Car::isElectric )
                .collect(Collectors.toList());
    }


    /** === VIEW ALL BOOKINGS === **/
    public List<CarBooking> getAllBookings() {
        return carBookingDAO.getBookings();
    }

}

