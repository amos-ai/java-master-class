package com.amos.booking;

import java.util.List;
import java.util.UUID;

public interface CarBookingDAO {
    List<CarBooking> getBookings();
    boolean carIsBooked(UUID carId);
    CarBooking findBookingById(UUID bookingId);
    void saveBooking(CarBooking booking);
    void deleteBooking(UUID bookingId);
}
