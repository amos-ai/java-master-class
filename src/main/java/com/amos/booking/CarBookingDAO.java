package com.amos.booking;

import java.util.UUID;

public interface CarBookingDAO {
    boolean carIsBooked(UUID carId);
    CarBooking[] getBookings();
    CarBooking findBookingById(UUID bookingId);
    void saveBooking(CarBooking booking);
    void deleteBooking(UUID bookingId);
}
