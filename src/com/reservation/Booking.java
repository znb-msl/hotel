package com.reservation;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Booking {
    private User user;
    private Room room;
    private Date checkIn;
    private Date checkOut;
    private int bookingTotal;
    private int roomPrice;

    public Booking(User user, Room room, Date checkIn, Date checkOut, int bookingTotal, int roomPrice) {
        this.user = user;
        this.room = room;
        this.checkIn = removeTime(checkIn);
        this.checkOut = removeTime(checkOut);
        this.bookingTotal = bookingTotal;
        this.roomPrice = roomPrice;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getBookingTotal() {
        return bookingTotal;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

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

    public boolean overlaps(Date start, Date end) {
        // booking overlaps if start < this.checkOut && end > this.checkIn
        return start.before(checkOut) && end.after(checkIn);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Booking{" +
                "user=" + user +
                ", room=" + room +
                ", checkIn=" + sdf.format(checkIn) +
                ", checkOut=" + sdf.format(checkOut) +
                ", bookingTotal= "+ bookingTotal +
                ", roomPrice= "+ roomPrice +
                '}';
    }
}

