package com.amos.booking;

import java.io.*;
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

                CarBooking[] bookings = getBookings();

                for (CarBooking booking : bookings) {
                        if (booking != null
                                && booking.getCar() != null
                                && booking.getCar().getUuid() != null
                                && booking.getCar().equals(carId)) {
                                return true;
                        }
                }
                return false;
        }

        @Override
        public void saveBooking(CarBooking booking) {
                // Read existing bookings from file
                // Add new booking
                // Write all bookings back to file
                CarBooking[] existingBooking = loadFromFile();

                CarBooking[] updatedBooking = new CarBooking[existingBooking.length + 1];

                for (int i = 0; i < existingBooking.length; i++) {
                        updatedBooking[i] = existingBooking[i];
                }
                updatedBooking[updatedBooking.length - 1] = booking;

                saveToFile(updatedBooking);

        }

        @Override
        public void deleteBooking(UUID bookingId) {
                // Read existing bookings from file
                // Remove booking with matching ID
                // Write remaining bookings back to file
                CarBooking[] existingBookings = loadFromFile();
                int count = 0;
                for (CarBooking booking : existingBookings) {
                        if (booking != null &&
                        booking.getUuid().equals(bookingId)) {
                                count++;
                        }
                }

                CarBooking[] remainingBookings = new CarBooking[count];

                int index = 0;

                for (CarBooking booking : existingBookings) {
                        if (booking != null &&
                        !booking.getUuid().equals(bookingId)) {
                                remainingBookings[index] = booking;
                                index++;
                        }
                }

                saveToFile(remainingBookings);
        }

        @Override
        public CarBooking[] getBookings() {
                return loadFromFile();
        }

        @Override
        public CarBooking findBookingById(UUID bookingId) {
                CarBooking[] bookings = loadFromFile();

                for (CarBooking booking : bookings) {
                        if (booking != null && booking.getUuid().equals(bookingId)) {
                                return booking;
                        }
                }
                return null;
        }

// -------------------------
// SERIALIZATION
// -------------------------

private void saveToFile(CarBooking[] bookings) {
                try (ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(filePath))) {

                        output.writeObject(bookings);

                }catch (IOException e) {
                        throw new RuntimeException(
                                "Could not save booking to file");
                }
        }

// -------------------------
// DESERIALIZATION
// -------------------------

 private CarBooking[] loadFromFile() {
         File file = new File(filePath);

         if (!file.exists()) {
                 return new CarBooking[0];
         }
         try (ObjectInputStream input =
                      new ObjectInputStream(
                              new FileInputStream(file))) {
                 return (CarBooking[]) input.readObject();
         }catch (IOException | ClassNotFoundException e) {
                 throw new RuntimeException(
                         "Couldn't bookings from file", e);
         }
   }

}
