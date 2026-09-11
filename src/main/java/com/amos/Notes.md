 for the first implementation you would create some sort of static initializer
 that initialises a single or an array of cars - lets assume the car already exists,
 you then can create the following models like carBooking, Car, User etc.
 Within your main method you’d get the user input from the console based on the user
 making the booking and the car needed to be booked(we already created the car).
 Then based off of that you can call your booking service and save the booking made etc etc.
 It definitely becomes a lot easier when you move along the phases. I hope that helps







        FUNCTIONAL REQUIREMENTS

 | Requirement          | Main/CLI            | Service                               | DAO                    | Arrays                            |
 | -------------------- | ------------------- | ------------------------------------- | ---------------------- | --------------------------------- |
 | FR-01 Book Car       | Input/output        | **Main business logic**               | Find/store             | `User[]`, `Car[]`, `CarBooking[]` |
 | FR-02 Delete Booking | Input/output        | **Cancellation logic**                | Find/update            | `CarBooking[]`                    |
 | FR-03 User Bookings  | Input/output        | Filter/validate                       | Retrieve bookings      | `CarBooking[]`                    |
 | FR-04 All Bookings   | Input/output        | Maybe filtering                       | Retrieve bookings      | `CarBooking[]`                    |
 | FR-05 Available Cars | Input/output        | **Availability logic**                | Retrieve cars/bookings | `Car[]`, `CarBooking[]`           |
 | FR-06 Electric Cars  | Input/output        | **Electric + availability filtering** | Retrieve data          | `Car[]`, `CarBooking[]`           |
 | FR-07 All Users      | Input/output        | Simple retrieval                      | Retrieve users         | `User[]`                          |
 | FR-08 Exit           | **Exit/menu logic** | —                                     | —                      | —                                 |



1. BOOK A CAR
    |
    |__ find user ID
    |__ Find Car --
    |__ Validate date
    |__ Calculate Price
    |__ Check if car  already exist
    |