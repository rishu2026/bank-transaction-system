package com.bank.service;

import com.bank.model.Account;

public class TransactionService {

    public static void deposit(Account acc, double amount) {
        acc.setBalance(acc.getBalance() + amount);
    }

    public static void withdraw(Account acc, double amount) {
        if (amount > acc.getBalance()) {
            throw new RuntimeException("Insufficient Balance");
        }
        acc.setBalance(acc.getBalance() - amount);
    }
}
