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

        if (countBooking < bookings.length) {
            bookings[countBooking] = booking;
            countBooking ++;
        }else {
            System.out.println("Storage is Full");
        }
    }

    public CarBooking findBookingId(UUID uuid) {

        for (CarBooking booking : bookings) {
            if (booking != null && booking.getUuid().equals(uuid)) {
                return booking;
            }
        }
        throw new IllegalArgumentException("Booking doesn't exist");
    }

    public boolean carIsBooked(UUID carId) {

        if (carId == null) {
            return false;
        }

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null
                    && bookings[i].getCar() != null
                    && bookings[i].getCar().getUuid() != null) {

                if (carId.equals(bookings[i].getCar().getUuid())){
                    return true;
                }
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
    }
}

