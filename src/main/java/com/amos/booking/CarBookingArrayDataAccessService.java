package com.amos.booking;

import java.util.UUID;


public class CarBookingArrayDataAccessService implements CarBookingDAO {

    private CarBooking[] bookings;
    private int countBooking;

    public CarBookingArrayDataAccessService(int storage) {

        bookings = new CarBooking[storage];
        countBooking = 0;
    }

    @Override
    public boolean carIsBooked(UUID carId) {

        if (carId == null) {
            return false;
        }
        for (CarBooking booking : bookings) {
            if (booking != null
            && booking.getCar() != null
            && booking.getCar().getUuid() != null
            && booking.getCar().getUuid().equals(carId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CarBooking[] getBookings() {
        return bookings;
    }

    @Override
    public CarBooking findBookingById(UUID bookingId) {

        for (CarBooking booking : bookings) {
            if (booking != null && booking.getUuid().equals(bookingId)) {
                return booking;
            }
        }
        throw new IllegalArgumentException("Booking doesn't exist");
    }


    @Override
    public void saveBooking(CarBooking booking) {

        if (countBooking < bookings.length) {
            bookings[countBooking] = booking;
            countBooking ++;
        }else {
            throw new IllegalStateException("Booking Storage is full");
        }
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null && bookings[i].getUuid().equals(bookingId)) {
                bookings[i] = null;
                return;
            }
        }
        throw new IllegalArgumentException("Booking doesn't exist");
    }
}



