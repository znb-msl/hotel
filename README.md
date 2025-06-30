# Technical Test 2 - Hotel Reservation System

## Summary

This project implements a simplified Hotel Reservation System in Java.  
It manages three main entities:
- **User**, defined by their balance
- **Room**, defined by its type and price per night
- **Booking**, designed to handle reservations between users and rooms

The system allows users to book available rooms, provided they have enough balance for the booking duration.

---

## Main Features

- Create and manage users with an initial balance
- Create and manage rooms with types (standard suite, junior suite, master suite) and nightly prices
- Book rooms for a specified period, checking availability and user balance
- Update user balance upon successful booking
- Print all rooms and bookings from newest to oldest
- Print all users from newest to oldest

---

## Technical Details

- Bookings can only be made if the room is free during the requested period and the user has sufficient balance
- Modifying a room using `setRoom(...)` must not affect existing bookings
- `setRoom(...)` and `setUser(...)` create new rooms or users if they don’t already exist
- Data is stored using `ArrayList`s only, without repositories
- Dates for bookings consider only year, month, and day
- Exceptions are handled for invalid inputs (e.g., invalid dates, insufficient balance, room already booked)

---

## Test Scenario Example

- Create 3 rooms:
    - ID 1: Standard Suite, 1000€/night
    - ID 2: Junior Suite, 2000€/night
    - ID 3: Master Suite, 3000€/night

- Create 2 users:
    - ID 1, balance 5000€
    - ID 2, balance 10000€

- Booking attempts:
    - User 1 books Room 2 from 30/06/2026 to 07/07/2026 (7 nights)
    - Attempt with invalid period (check-out before check-in)
    - User 1 books Room 1 for 1 night
    - User 2 books Room 1 for 2 nights (overlapping booking to handle)
    - User 2 books Room 3 for 1 night
    - Update Room 1’s type and price with `setRoom(1, MASTER_SUITE, 10000)` without affecting past bookings

---

## Design Questions (Bonus)

1. **Is it recommended to put all functions in the same Service class?**  
   No. It is better to separate responsibilities across multiple classes to improve maintainability, testability, and clarity, following the Single Responsibility Principle.

2. **Alternative to `setRoom(...)` that does not affect previous bookings?**  
   Another approach would be to forbid modifying existing rooms directly and instead create new room instances for updates, ensuring immutability of historical data. This guarantees consistency of past bookings.

---

## Instructions

- The project is implemented in Java using only standard data structures (`ArrayList`).
- To test, use the provided `Service` class and its exposed methods.
- All errors and invalid cases are handled with custom exceptions.

---

## Screenshots

(Screenshots of the `printAll()` and `printAllUsers()` outputs to be added here)

---

Thank you for reviewing this project!  
Feel free to reach out for any questions or suggestions.

