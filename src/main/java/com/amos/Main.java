package com.amos;

import com.amos.booking.CarBooking;
import com.amos.booking.CarBookingDao;
import com.amos.booking.CarBookingService;
import com.amos.car.Brand;
import com.amos.car.Car;
import com.amos.car.CarDao;
import com.amos.car.CarService;
import com.amos.user.User;
import com.amos.user.UserDao;
import com.amos.user.UserService;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;


import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;


public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        UserDao userDao = new UserDao();
        CarDao carDa0 = new CarDao();
        CarBookingDao carBookingDao = new CarBookingDao(5);


        UserService userService = new UserService(userDao);
        CarService carService = new CarService(carDa0);
        CarBookingService carBookingService = new CarBookingService(
                carBookingDao,
                userService,
                carService
        );


        for (User user : userService.getAllUsers()) {
            System.out.println("Name:" + user.getName() + " " + "userId: " + user.getUuid());
        }

        for (Car car : carService.getAllCars()) {
            System.out.println("Brand: " + car.getBrand() + "ID: " + car.getUuid() + " " +
                    "regNumber: " + car.getRegNumber() + " " + "Price:" + car.getRentalPricePerDay()
                    + " " + "isElectric: " + car.isElectric());

        }


        boolean runningApp = true;

        while (runningApp) {

            System.out.println();
            System.out.println("""
                       1. Book a Car
                       2. Delete Booking
                       3. View User Booking;
                       4. View All Bookings
                       5. View Available Cars
                       6. View Electric Cars
                       7. View All Users
                       8. Exit
                       System.out.print("Choose an option
                    """);


            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> bookCar(carBookingService, userService, carService, scanner);

                case 2 -> deleteBooking(carBookingService, scanner);
                case 3 -> carBookingService.getUserBooking(fromString(scanner.nextLine()));
                case 4 -> getAllBookings(carBookingService, scanner);
                case 5 -> System.out.println(Arrays.toString(carBookingService.getAvailableCars()));
                case 6 -> getAvailableElectricCars(carBookingService, scanner);
                case 7 -> getAllUsers(userService, scanner);
                case 8 -> runningApp = false;
                default -> System.out.println("Invalid Option.");
            }
        }

    }


    private static void bookCar(CarBookingService carBookingService,
                                UserService userService,
                                CarService carService,
                                Scanner scanner) {

        try {
            scanner.nextLine();
            System.out.println("Enter user ID:");
            UUID userId = UUID.fromString(scanner.nextLine());

            System.out.println("Enter car ID:");
            UUID carId = UUID.fromString(scanner.nextLine());

            System.out.println("Enter start date (YYYY-MM-DD):");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Enter end date (YYYY-MM-DD):");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            CarBooking booking = carBookingService.bookCar(
                    userId,
                    carId,
                    startDate,
                    endDate
            );

            System.out.println("Booking created:");
            System.out.println(booking);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void deleteBooking(CarBookingService carBookingService,
                                      Scanner scanner) {

        try {
            scanner.nextLine();
            CarBooking[] bookings = carBookingService.getAllBookings();
            if (bookings.length == 0) {
                System.out.println("There are not active bookings");
            } else {
                System.out.println(Arrays.toString(bookings));
                System.out.print("Select a booking id to delete ");
                String bookingId = scanner.nextLine();

                if(carBookingService.deleteBooking(UUID.fromString(bookingId))) {
                    System.out.println("Booking %s deleted successfully".formatted(bookingId));
                } else {
                    System.out.println("Unable to delete booking with id: %s".formatted(bookingId));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private static void getAllBookings(CarBookingService carBookingService, Scanner scanner) {
        try {
            scanner.nextLine();
            CarBooking[] bookings = carBookingService.getAllBookings();

            for (CarBooking allBooking : bookings) {
                System.out.println(allBooking);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void getAllUsers(UserService userService, Scanner scanner) {
        try{
            scanner.nextLine();
            System.out.println(" View All Users");
            User[] allUser = userService.getAllUsers();

            for (User user : allUser) {
                if (user != null) {
                System.out.println(user.getName());
                }
        }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void getAvailableElectricCars(CarBookingService carBookingService,
                                                 Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("View Available Electric Cars");
            Car[] electricCars = carBookingService.getAVailableElectricCars();

            for (Car car : electricCars) {
                System.out.println("- " + car);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}
