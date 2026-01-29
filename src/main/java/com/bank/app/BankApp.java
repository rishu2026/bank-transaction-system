package com.bank.app;

import com.bank.model.Account;
import com.bank.service.TransactionService;

public class BankApp {

    public static void main(String[] args) {

        Account acc = new Account(1001, "Rishu Kumar", 5000);

        TransactionService.deposit(acc, 2000);
        TransactionService.withdraw(acc, 1000);

        System.out.println("Account Holder : " + acc.getHolderName());
        System.out.println("Final Balance  : " + acc.getBalance());

        // Build-time validation (Jenkins killer feature 🔥)
        if (acc.getBalance() == 6000) {
            System.out.println("Transaction Test PASSED ✅");
        } else {
            System.out.println("Transaction Test FAILED ❌");
            System.exit(1); // Jenkins build FAIL
        }
    }
}
