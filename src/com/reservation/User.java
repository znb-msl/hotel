package com.reservation;

// Class representing a user
public class User {
    // User's unique identifier
    private int userId;
    // User's account balance
    private int balance;

    // Constructor to initialize the user with an ID and balance
    public User(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    // Returns the user's ID
    public int getUserId() {
        return userId;
    }

    // Returns the user's balance
    public int getBalance() {
        return balance;
    }

    // Deducts the specified amount from the balance if sufficient
    public void debit(int amount) {
        // Check if the balance is enough
        if(amount > balance) {
            // Throw an exception if balance is insufficient
            throw new IllegalArgumentException("Insufficient balance");
        }
        // Subtract the amount from the balance
        balance -= amount;
    }

    // Returns a string representation of the user
    public String toString() {
        return "User{" + "userId=" + userId + ", balance=" + balance + '}';
    }
}
