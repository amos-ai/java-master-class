package com.amos.booking;

import org.w3c.dom.stylesheets.LinkStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingFileDataAccessService implements CarBookingDAO {
    private final String filePath;

    public CarBookingFileDataAccessService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean carIsBooked(UUID carId) {
        if (carId == null) {
            return false;
        }

        List<CarBooking> bookings = getBookings();

        for (CarBooking booking : bookings) {
            if (booking != null
                    && booking.getCar() != null
                    && carId.equals(booking.getCar().getUuid())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveBooking(CarBooking booking) {

        if (booking == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null"
            );
        }

        List<CarBooking> bookings = loadFromFile();
        bookings.add(booking);

        saveToFile(bookings);

    }

    @Override
    public void deleteBooking(UUID bookingId) {

        if (bookingId == null) {
            throw new IllegalArgumentException(
                    "Booking ID cannot be null"
            );
        }
        List<CarBooking> bookings = loadFromFile();

        boolean removed = bookings.removeIf(
                booking -> booking != null
                            && bookingId.equals(booking.getUuid())
        );

        if (!removed) {
            throw new IllegalArgumentException(
                    "Booking doesn't exist"
            );
        }

        saveToFile(bookings);
    }

    @Override
    public List<CarBooking> getBookings() {

        return new ArrayList<> (loadFromFile());
    }

    @Override
    public CarBooking findBookingById(UUID bookingId) {
        //CarBooking[] bookings = loadFromFile();

       if (bookingId != null) {

        for (CarBooking booking : loadFromFile()) {
            if (booking != null && bookingId.equals(booking.getUuid())) {
                return booking;
            }
         }
       }
        throw new IllegalArgumentException("Booking doesn't exist");
    }


    private void saveToFile(List<CarBooking> bookings) {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(filePath))) {

            output.writeObject(bookings);

        } catch (IOException  e) {
            throw new RuntimeException(
                    "Could not save booking to file", e);
        }
    }


    private List<CarBooking> loadFromFile() {
            File file = new File(filePath);

            if (!file.exists()) {
                return new ArrayList<>();
            }

            try (ObjectInputStream input =
                         new ObjectInputStream(
                                 new FileInputStream(file))) {

                Object data = input.readObject();

                // Handle data saved by the old array implementation
                if (data instanceof CarBooking[] bookingsArray) {
                    return new ArrayList<>(List.of(bookingsArray));
                }

                // Handle data saved by the new List implementation
                if (data instanceof List<?> bookingsList) {
                    List<CarBooking> bookings = new ArrayList<>();

                    for (Object item : bookingsList) {
                        if (!(item instanceof CarBooking)) {
                            throw new IOException(
                                    "File contains invalid booking data");
                        }

                        bookings.add((CarBooking) item);
                    }

                    return bookings;
                }

                throw new IOException("Unexpected booking data format");

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(
                        "Couldn't load bookings from file", e);
            }
    }
}
