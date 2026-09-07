package com.amos.booking;

import java.util.UUID;

public class CarBookingDao {
    /**
     * add booking - storage(capacity), count if there's space.
     * find booking by ID -id, iterate carbooking, if id exist in booking, return id
     * find all booking
     * **/

    private CarBooking [] bookings;
    private int countBooking;

    public CarBookingDao(int storage) {
        // assign new storage to bookings
        // assign countBooking to 0
        bookings = new CarBooking[storage];
        countBooking = 0;
    }

    public void addBooking(CarBooking booking) {
        // check if countBooking is <= storage
        // if yes, add booking, increment count
        // if not, raise an exception with message
        if (countBooking <= bookings.length) {
            bookings[countBooking] = booking;
            countBooking ++;
        }else {
            System.out.println("Storage is Full");
        }
    }

    public CarBooking findBookingId(UUID uuid) {
        // for booking in carbooking - search through bookings
        // if id already exist in boooking
        // return id
        // if not - booking id doesn't exist,
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
        // if carid exist in booking
        // return true
        for (int i = 0; i < countBooking; i++) {
            if (bookings[i].getCar().getUuid().equals(carId)) {
                return true;
            }
        }
        return false;
    }

    public CarBooking[] findAllBooking() {
        // return all bookings array
        return bookings;
    }

        public void delete(UUID bookingId) {

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

    }
            /** === VIEW USER BOOINGS === **/
            /*
            public CarBooking getUserBooking(UUID user) {
                for (CarBooking booking : bookings) {
                    if (booking != null && user.equals(user)) {
                        return booking;
                    } else {
                        throw new IllegalArgumentException("User booking is not equal");
                    }
                }
                return null;
            }
             */

            /** === VIEW ALL BOOKINGS === **/


            /** === VIEW AVAILABLE CARS === **/


            /** === VIEW ELECTRIC CARS === **/


            /** === VIEW ALL USER === **/


            /** === EXIT === **/
}
