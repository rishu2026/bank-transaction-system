package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.TransactionService;

import javax.swing.*;
import java.awt.*;

public class BankUI extends JFrame {

    private Account account;
    private JLabel balanceLabel;
    private JTextField amountField;

    public BankUI() {

        // Dummy logged-in user (real apps me DB se aata)
        account = new Account(1001, "Rishu Kumar", 5000);

        setTitle("Bank Transaction System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Top panel
        JLabel userLabel = new JLabel("Welcome, " + account.getHolderName(), SwingConstants.CENTER);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(userLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        balanceLabel = new JLabel("Balance: ₹" + account.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));

        amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("Enter Amount"));

        centerPanel.add(balanceLabel);
        centerPanel.add(amountField);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        depositBtn.addActionListener(e -> performDeposit());
        withdrawBtn.addActionListener(e -> performWithdraw());
    }

    private void performDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            TransactionService.deposit(account, amount);
            updateBalance();
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void performWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            TransactionService.withdraw(account, amount);
            updateBalance();
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: ₹" + account.getBalance());
        amountField.setText("");
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankUI().setVisible(true));
    }
}
