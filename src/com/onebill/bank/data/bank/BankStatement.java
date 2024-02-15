package com.onebill.bank.data.bank;

import com.onebill.bank.service.BankService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BankStatement {
    String username;
    String type;
    double amount;
    double userBalance;
    Date transactionDate;
    String bankName;

    public BankStatement(String username, String type, double amount, double userBalance, Date transactionDate, String bankName) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.userBalance = userBalance;
        this.transactionDate = transactionDate;
        this.bankName = bankName;
    }

    public void printStatement() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("\nBankStatement:");
        System.out.println(
                "\nUsername: " + BankService.getUsername() +
                        "\nType: " + type +
                        "\nAmount: " + amount +
                        "\nUser Balance: " + BankService.getBalance() +
                        "\nTransaction Date: " + dateFormat.format(transactionDate) +
                        "\nBank Name: " + BankService.getBankName() + "\n");
    }
}
