package com.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Service class that manages rooms, users, and bookings
public class Service {
    // List of all rooms
    private ArrayList<Room> rooms = new ArrayList<>();
    // List of all users
    private ArrayList<User> users = new ArrayList<>();
    // List of all bookings
    private ArrayList<Booking> bookings = new ArrayList<>();

    // Adds or updates a room based on its room number
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        Room existingRoom = findRoom(roomNumber);
        if (existingRoom == null) {
            // Room does not exist, so add a new one
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        } else {
            // Update room type and price; existing bookings are not affected
            existingRoom.setType(roomType);
            existingRoom.setPricePerNight(roomPricePerNight);
        }
    }

    // Adds a new user or ignores if user already exists
    public void setUser(int userId, int balance) {
        User existingUser = findUser(userId);
        if (existingUser == null) {
            // User does not exist, so add a new one
            users.add(new User(userId, balance));
        } else {
            // Optionally, update balance or leave unchanged
        }
    }

    // Books a room for a user between two dates
    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        User user = findUser(userId);
        Room room = findRoom(roomNumber);

        // Check if user and room exist
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room does not exist");
        }

        // Check for valid date range
        if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
            throw new IllegalArgumentException("Invalid check-in and check-out dates");
        }

        // Normalize dates by removing time part
        checkIn = stripTime(checkIn);
        checkOut = stripTime(checkOut);

        // Check if the room is already booked for the given period
        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == roomNumber) {
                if (b.overlaps(checkIn, checkOut)) {
                    throw new IllegalStateException("Room is already booked for this period");
                }
            }
        }

        // Calculate the number of nights
        long diff = checkOut.getTime() - checkIn.getTime();
        int nights = (int) (diff / (1000 * 60 * 60 * 24));

        // Compute the total cost of the booking
        int totalCost = nights * room.getPricePerNight();

        // Check if user has enough balance
        if (user.getBalance() < totalCost) {
            throw new IllegalStateException("User has insufficient balance");
        }

        // Deduct the booking cost from the user's balance
        user.debit(totalCost);

        // Create and store the new booking
        bookings.add(new Booking(user, room, checkIn, checkOut, totalCost, room.getPricePerNight()));
    }

    // Prints all rooms and bookings in reverse order
    public void printAll() {
        System.out.println("Rooms:");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            System.out.println(rooms.get(i));
        }

        System.out.println("\nBookings:");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            System.out.println(bookings.get(i));
        }
    }

    // Prints all users in reverse order
    public void printAllUsers() {
        System.out.println("Users:");
        for (int i = users.size() - 1; i >= 0; i--) {
            System.out.println(users.get(i));
        }
    }

    // Searches for a room by its number
    private Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    // Searches for a user by their ID
    private User findUser(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    // Strips the time component from a date (keeps only the date part)
    private Date stripTime(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(sdf.format(date));
        } catch (Exception e) {
            return date;
        }
    }
}
