package com.amos.booking;

import java.util.UUID;

public class CarBookingDao {

    private CarBooking [] bookings;
    private int countBooking;

    public CarBookingDao(int storage) {

        bookings = new CarBooking[storage];
        countBooking = 0;
    }

    public void addBooking(CarBooking booking) {

        if (countBooking <= bookings.length) {
            bookings[countBooking] = booking;
            countBooking ++;
        }else {
            System.out.println("Storage is Full");
        }
    }

    public CarBooking findBookingId(UUID uuid) {

        for (CarBooking booking : bookings) {
            if (booking != null && booking.equals(uuid)) {
                return booking;
            } else {
                throw new IllegalArgumentException("Booking doesn't exist");
            }
        }
        return null;
    }

    public boolean carIsBooked(UUID carId) {

        for (int i = 0; i < countBooking; i++) {
            if (bookings[i].getCar().getUuid().equals(carId)) {
                return true;
            }
        }
        return false;
    }

    public CarBooking[] findAllBooking() {
        return bookings;
    }

        public  boolean deleteBookingById(UUID bookingId) {
            for (int i = 0; i < bookings.length; i++) {
                if (bookings[i] != null && bookings[i].getUuid().equals(bookingId)) {
                    bookings[i] = null;
                    return true;
                }
            }
            return false;

          /**
        for (int i = 0; i < countBooking; i++) {
            if (bookings[i].getUuid().equals(bookingId)) {
                for (int j = i; j < countBooking - 1; j++) {
                    bookings[j] = bookings[j + 1];
                }

                    bookings[countBooking - 1] = null;
                    countBooking--;

                    return;
                }
            }
           **/

    }

}
