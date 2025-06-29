package com.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Service {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        Room existingRoom = findRoom(roomNumber);
        if (existingRoom == null) {
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        } else {
            // Update only room info but do not affect previous bookings
            existingRoom.setType(roomType);
            existingRoom.setPricePerNight(roomPricePerNight);
        }
    }

    public void setUser(int userId, int balance) {
        User existingUser = findUser(userId);
        if (existingUser == null) {
            users.add(new User(userId, balance));
        } else {
            // Optionally, update balance or ignore
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        User user = findUser(userId);
        Room room = findRoom(roomNumber);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room does not exist");
        }

        if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
            throw new IllegalArgumentException("Invalid check-in and check-out dates");
        }

        // Normalize dates (strip time)
        checkIn = stripTime(checkIn);
        checkOut = stripTime(checkOut);

        // Check if room is free
        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == roomNumber) {
                if (b.overlaps(checkIn, checkOut)) {
                    throw new IllegalStateException("Room is already booked for this period");
                }
            }
        }

        // Calculate number of nights
        long diff = checkOut.getTime() - checkIn.getTime();
        int nights = (int) (diff / (1000 * 60 * 60 * 24));

        int totalCost = nights * room.getPricePerNight();

        if (user.getBalance() < totalCost) {
            throw new IllegalStateException("User has insufficient balance");
        }

        // Deduct user balance
        user.debit(totalCost);

        // Create booking
        bookings.add(new Booking(user, room, checkIn, checkOut, totalCost, room.getPricePerNight()));
    }

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

    public void printAllUsers() {
        System.out.println("Users:");
        for (int i = users.size() - 1; i >= 0; i--) {
            System.out.println(users.get(i));
        }
    }

    private Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    private User findUser(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    private Date stripTime(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(sdf.format(date));
        } catch (Exception e) {
            return date;
        }
    }
}

