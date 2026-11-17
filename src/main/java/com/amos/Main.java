package com.amos;

import com.amos.booking.CarBooking;
import com.amos.booking.CarBookingDAO;
import com.amos.booking.CarBookingService;
import com.amos.booking.CarBookingArrayDataAccessService;
import com.amos.booking.CarBookingFileDataAccessService;
import com.amos.car.Car;
import com.amos.car.CarArrayDataAccessService;
import com.amos.car.CarDAO;
import com.amos.car.CarService;
import com.amos.user.User;
import com.amos.user.UserArrayDataAccessService;
import com.amos.user.UserDAO;
import com.amos.user.UserService;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        UserDAO userArrayDataAccessService = new UserArrayDataAccessService();
        UserService userService = new UserService(userArrayDataAccessService);

        CarDAO carArrayDataAccessService = new CarArrayDataAccessService();
        CarService carService = new CarService(carArrayDataAccessService);

        CarBookingDAO carBookingDAO = new CarBookingFileDataAccessService("bookings.dat");
        //CarBookingDAO carBookingDAO = new CarBookingArrayDataAccessService(5);

        CarBookingService carBookingService = new CarBookingService(
                carBookingDAO,
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
                case 3 -> getUserBooking(carBookingService, scanner);
                case 4 -> getAllBookings(carBookingService, scanner);
                case 5 -> getAvailableCars(carBookingService, scanner);
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
            List<CarBooking> bookings = carBookingService.getAllBookings();
            if (bookings.isEmpty()) {
                System.out.println("There are not active bookings");
            } else {
                System.out.println(bookings);
                System.out.print("Select a booking id to delete ");
                String bookingId = scanner.nextLine();

                carBookingService.deleteBooking(UUID.fromString(bookingId));

                System.out.println("Booking %s deleted successfully".formatted(bookingId));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private static void getAllBookings(CarBookingService carBookingService, Scanner scanner) {
        try {
            scanner.nextLine();
            List<CarBooking> bookings = carBookingService.getAllBookings();

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
            List<User> allUser = userService.getAllUsers();

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
            List<Car> electricCars = carBookingService.getAvailableElectricCars();

            for (Car car : electricCars) {
                System.out.println("- " + car);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getUserBooking(CarBookingService carBookingService,
                                       Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Put UserBooking Id: ");
            String userID = scanner.nextLine();

            UUID id = UUID.fromString(userID);
            List<CarBooking> bookingID = carBookingService.getUserBooking(id);

            System.out.println("UserBookingId: " + bookingID);

        } catch (IllegalArgumentException e) {
            System.out.println("User Booking id not found");
        }
    }

    private static void getAvailableCars(
            CarBookingService carBookingService,
            Scanner scanner) {

        try {
            scanner.nextLine();

            List<Car> availableCars =
                    carBookingService.getAvailableCars();

            if (availableCars.isEmpty()) {
                System.out.println("No available cars.");
                return;
            }

            for (Car car : availableCars) {
                System.out.println("- " + car);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

