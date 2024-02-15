package com.onebill.bank.data.config;

public interface RBIBankCharges {

    double getWithdrawCharges(String bankName);

    double getDepositCharges(String bankName);

    double getMinimumBalance(String bankName);
}
