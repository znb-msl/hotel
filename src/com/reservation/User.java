package com.reservation;

public class User {
    private int userId;
    private int balance;

    public User(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public int getBalance() {
        return balance;
    }

    public void debit(int amount) {
        if(amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", balance=" + balance + '}';
    }
}

