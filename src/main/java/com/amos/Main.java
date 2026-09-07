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
import jdk.swing.interop.SwingInterOpUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;

/*
    Responsible for:
    - CLI
    - User Input
    - Display result
 */

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

                  /** User Static Data **/

        User james = new User(
                "d75751b4-d7fe-4e26-9d7f-38c880f9f7f1",
                "James");

        User jamila = new User(
        "2e0f7622-3d86-46ea-98e8-fd914d40c989",
                "Jamila");

        User Dave = new User(
                "97aade92-534f-4d66-a174-b160dde8e2ae",
                "Dave");

        User Yasmin = new User(
                "0960f770-e4ce-4c8a-baa7-54eabe310c30",
                "Yasmin");

        User[] users = {james, jamila, Dave, Yasmin};


                     /** Car Static Data **/

        Car car1 = new Car(
                UUID.randomUUID(),
                "YT501Jk",
                new BigDecimal("50.00"),
                Brand.TOYOTA,
                false
        );

        Car car2 = new Car(
                UUID.randomUUID(),
                "XY101MN",
                new BigDecimal("70.00"),
                Brand.AUDI,
                true
        );

        Car car3 = new Car(
                UUID.randomUUID(),
                "XY54FT",
                new BigDecimal("80.00"),
                Brand.MERCEDES,
                false
        );

        Car car4 = new Car(
                UUID.randomUUID(),
                "TS101EV",
                new BigDecimal("95.00"),
                Brand.TESLA,
                true
        );

        Car[] cars = {
                car1,
                car2,
                car3,
                car4
        };

        UserDao userDao = new UserDao(users);
        CarDao carDa0 = new CarDao(cars);
        CarBookingDao carBookingDao = new CarBookingDao(5);


        UserService userService = new UserService(userDao);
        CarService carService= new CarService(carDa0);
        CarBookingService bookingService = new CarBookingService(
                carBookingDao,
                userService,
                carService
        );


        for (User user : userService.getAllUsers() ){
            System.out.println("Name:" + user.getName() + " " + "userId: " + user.getUuid() );
        }

        for (Car car : carService.getAllCars()) {
            System.out.println("Brand: " + car.getBrand() + "ID: " + car.getUuid() + " " +
                    "regNumber: " + car.getRegNumber() + " " + "Price:" + car.getRentalPricePerDay()
                    + " " + "isElectric: " + car.isElectric());

        }
 

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("===== CAR BOOKING SYSTEM =====");
            System.out.println("1. Book a Car");
            System.out.println("2. Delete Booking");
            System.out.println("3. View User Bookings");
            System.out.println("4. View All Bookings");
            System.out.println("5. View Available Cars");
            System.out.println("6. View Electric Cars");
            System.out.println("7. View All Users");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");


            // System.out.print("Select from menu (1-8): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":

                    System.out.println("Enter user ID:");
                    UUID userId = UUID.fromString(scanner.nextLine());

                    System.out.println("Enter car ID:");
                    UUID carId = UUID.fromString(scanner.nextLine());

                    System.out.println("Enter start date (YYYY-MM-DD):");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Enter end date (YYYY-MM-DD):");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    CarBooking booking = bookingService.bookCar(
                            userId,
                            carId,
                            startDate,
                            endDate
                    );

                    System.out.println("Booking created:");
                    System.out.println(booking);

                    break;

                case  "2":
                    System.out.println("Delete a booking:");
                    bookingService.deleteBookking(UUID.fromString(scanner.nextLine()));
                    break;

                case "3":
                    System.out.println("Enter user ID: ");
                    bookingService.getUserBooking(UUID.fromString(scanner.nextLine()));
                    break;

                case "4":
                    System.out.println("View all Bookings:");
                    CarBooking[] bookings = bookingService.getAllBookings();

                    for (CarBooking allBooking : bookings) {
                        System.out.println(allBooking);
                    }
                    break;
                case "5":
                    System.out.println("View Available Cars");
                    System.out.println(bookingService.getAvailableCars());
                    break;

                case "6":
                    System.out.println("View Available Electric Cars");
                    Car[] electricCars = bookingService.getAVailableElectricCars();

                    for (Car car : electricCars) {
                        System.out.println("- " + car);
                    }
                    break;

                case "7":
                    System.out.println(" View All Users");
                    User[] allUser = userService.getAllUsers();

                    for (User user : allUser) {
                        System.out.println(user);
                    }
                case "8":
                    running = false;
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid Option.");
            }
        }
        scanner.close();
        }







}
