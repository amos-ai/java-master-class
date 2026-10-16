package com.amos.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CarBookingArrayDataAccessService implements CarBookingDAO {

    private final List<CarBooking> bookings;
    private int countBooking;

    public CarBookingArrayDataAccessService() {

        this.bookings = new ArrayList<>();
    }

    @Override
    public boolean carIsBooked(UUID carId) {

        if (carId == null) {
            return false;
        }
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
    public List<CarBooking> getBookings() {

        return new ArrayList<>(bookings);
    }

    @Override
    public CarBooking findBookingById(UUID bookingId) {

        for (CarBooking booking : bookings) {
            if (booking != null
                    && bookingId != null
                    && bookingId.equals(booking.getUuid())) {
                return booking;
            }
        }
        throw new IllegalArgumentException("Booking doesn't exist");
    }


    @Override
    public void saveBooking(CarBooking booking) {

        if (booking == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null"
            );
        }
        bookings.add(booking);
        /**
        if (countBooking < bookings.size()) {
            bookings.set(countBooking, booking);
            countBooking ++;
        }else {
            throw new IllegalStateException("Booking Storage is full");
        }
         **/
    }

    @Override
    public void deleteBooking(UUID bookingId) {

        boolean removed = bookings.removeIf(
                booking -> booking != null
                                    && bookingId != null
                                    && bookingId.equals(booking.getUuid())
        );

        if (!removed) {
            throw new IllegalArgumentException(
                    "Booking doesn't exist"
            );
        }

        /**
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings[i]  && bookings[i].getUuid().equals(bookingId)) {
                bookings[i] = null;
                return;
            }
        }
        throw new IllegalArgumentException("Booking doesn't exist");
    }
         **/
    }
}



