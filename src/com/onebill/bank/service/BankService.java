package com.onebill.bank.service;

import java.util.ArrayList;
import java.util.Date;

import com.onebill.bank.data.config.AllConfigBankData;
import com.onebill.bank.data.user.AllUserBankData;
import com.onebill.bank.data.bank.*;


//class BankStatement {
//    String username;
//    String type;
//    double amount;
//    double userBalance;
//    Date transactionDate;
//    String bankName;
//
//    public BankStatement(String username, String type, double amount, double userBalance, Date transactionDate, String bankName) {
//        this.username = username;
//        this.type = type;
//        this.amount = amount;
//        this.userBalance = userBalance;
//        this.transactionDate = transactionDate;
//        this.bankName = bankName;
//    }
//
//    public void printStatement() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("\nBankStatement:");
//        System.out.println(
//                "\nUsername: " + username +
//                "\nType: " + type +
//                "\nAmount: " + amount +
//                "\nUser Balance: " + userBalance +
//                "\nTransaction Date: " + dateFormat.format(transactionDate) +
//                "\nBank Name: " + bankName + "\n");
//    }
//
//
//}

public class BankService {

    private static AllUserBankData user;
    private static String bankName;
    private int withdrawalCount;
    public ArrayList<BankStatement> transactions; // List to store transactions

    public BankService(AllUserBankData user, String bankName) {
        BankService.user = user;
        BankService.bankName = bankName;
        this.withdrawalCount = 0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
    	AllConfigBankData allConfig = new AllConfigBankData();
    	double depositCharge = allConfig.getDepositCharges(bankName) * amount;
        double totalAmountDeposited = amount - depositCharge;
        user.setBalance(user.getBalance() + totalAmountDeposited);

        // Record transaction
        transactions.add(new BankStatement(user.getName(), "Deposit", amount, user.getBalance(), new Date(), bankName));

        System.out.println("Deposited: " + amount + ", Deposit Charge for " + bankName + ": " + depositCharge);
    }
    
    public static String getUsername() {
        return user.getName();
    }

    public static double getBalance() {
        return user.getBalance();
    }

    public static String getBankName() {
        return bankName;
    }

    public void withdraw(double amount) {
    	AllConfigBankData allConfig = new AllConfigBankData();

        if (withdrawalCount >= 3) {
            System.err.println("Maximum withdrawal limit of 3 reached for today.");
            return;
        }

        double withdrawCharge = allConfig.getWithdrawCharges(bankName) * amount;
        double totalAmountWithdrawn = amount + withdrawCharge;
        double minimumBalance = allConfig.getMinimumBalance(bankName);

        if (totalAmountWithdrawn > user.getBalance()) {
            System.err.println("Insufficient balance");
            return;
        }

        if (user.getBalance() - totalAmountWithdrawn < minimumBalance) {
            System.err.println("Withdrawal failed: Minimum balance of " + minimumBalance + " must be maintained.");
            return;
        } else {
            user.setBalance(user.getBalance() - totalAmountWithdrawn);

            // Record transaction
            transactions.add(new BankStatement(user.getName(), "Withdraw", amount, user.getBalance(), new Date(), bankName));

            System.out.println("Withdrawn: " + amount + ", Withdraw Charge for " + bankName + ": " + withdrawCharge);
            withdrawalCount++;
        }
    }

    public void showBalance() {
        System.out.println("Current Balance: " + user.getBalance());
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No previous transactions.");
        } else {
            System.out.println("Previous Transactions:");
            for (BankStatement statement : transactions) {
                statement.printStatement(); // Explicitly call the printStatement method
            }
        }
    }

}


