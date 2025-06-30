package com.reservation;

import java.util.Date;
import java.text.SimpleDateFormat;

// Class representing a hotel room booking
public class Booking {
    // The user who made the booking
    private User user;
    // The room that is booked
    private Room room;
    // Check-in date
    private Date checkIn;
    // Check-out date
    private Date checkOut;
    // Total cost of the booking
    private int bookingTotal;
    // Price per night for the room at the time of booking
    // We did not calculate the room price at booking time based on the total amount paid and the stay duration,
    // because future improvements may introduce changes such as discounts. Therefore, we chose to store the
    // room price at the time of booking as a separate attribute for historical accuracy.
    private int roomPrice;

    // Constructor to initialize all booking details
    public Booking(User user, Room room, Date checkIn, Date checkOut, int bookingTotal, int roomPrice) {
        this.user = user;
        this.room = room;
        this.checkIn = removeTime(checkIn);
        this.checkOut = removeTime(checkOut);
        this.bookingTotal = bookingTotal;
        this.roomPrice = roomPrice;
    }

    // Returns the user who made the booking
    public User getUser() {
        return user;
    }

    // Returns the booked room
    public Room getRoom() {
        return room;
    }

    // Returns the check-in date
    public Date getCheckIn() {
        return checkIn;
    }

    // Returns the check-out date
    public Date getCheckOut() {
        return checkOut;
    }

    // Returns the total cost of the booking
    public int getBookingTotal() {
        return bookingTotal;
    }

    // Returns the room price per night
    public int getRoomPrice() {
        return roomPrice;
    }

    // Removes the time part from a Date object, keeping only the date
    private Date removeTime(Date date) {
        // Set hour, min, sec and ms to zero
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = sdf.format(date);
            return sdf.parse(formatted);
        } catch (Exception e) {
            return date;
        }
    }

    // Checks if this booking overlaps with another date range
    public boolean overlaps(Date start, Date end) {
        // Booking overlaps if start < this.checkOut and end > this.checkIn
        return start.before(checkOut) && end.after(checkIn);
    }

    // Returns a string representation of the booking
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Booking{" +
                "user=" + user +
                ", room=" + room +
                ", checkIn=" + sdf.format(checkIn) +
                ", checkOut=" + sdf.format(checkOut) +
                ", bookingTotal= "+ bookingTotal +
                ", Price per night for the room at the time of booking= "+ roomPrice +
                '}';
    }
}
