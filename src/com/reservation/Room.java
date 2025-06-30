package com.reservation;

// Class representing a room in the reservation system
public class Room {
    // The room's unique number
    private int roomNumber;
    // The type of the room (e.g., single, double, suite)
    private RoomType type;
    // The price per night for the room
    private int pricePerNight;

    // Constructor to initialize the room with its number, type, and price
    public Room(int roomNumber, RoomType type, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    // Returns the room's number
    public int getRoomNumber() {
        return roomNumber;
    }

    // Returns the room's type
    public RoomType getType() {
        return type;
    }

    // Returns the price per night for the room
    public int getPricePerNight() {
        return pricePerNight;
    }

    // Sets a new type for the room
    public void setType(RoomType type) {
        this.type = type;
    }

    // Sets a new price per night for the room
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    // Returns a string representation of the room
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type=" + type +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
