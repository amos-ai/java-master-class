package com.amos.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class CarBookingArrayDataAccessService implements CarBookingDAO {

    private final List<CarBooking> bookings;

    public CarBookingArrayDataAccessService() {

        this.bookings = new ArrayList<>();
    }

    @Override
    public boolean carIsBooked(UUID carId) {

        if (carId == null) {
            return false;
        }

        return bookings.stream()
                .filter(Objects::nonNull)
                .filter(booking ->
                        booking.getCar() != null)
                .anyMatch(booking ->
                        carId.equals(booking.getCar().getUuid()));
    }

    @Override
    public List<CarBooking> getBookings() {

        return new ArrayList<>(bookings);
    }

    @Override
    public CarBooking findBookingById(UUID bookingId) {

        return bookings.stream()
                .filter(Objects::nonNull)
                .filter(booking ->
                        bookingId.equals(booking.getUuid()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Booking doesn't exist"
                        ));
    }


    @Override
    public void saveBooking(CarBooking booking) {

        if (booking == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null"
            );
        }
        bookings.add(booking);

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
    }
}



