package com.onebill.bank.data.config;

public class AllConfigBankData implements RBIBankCharges {

    private static final double AXIS_WITHDRAW_CHARGES = 0.14;
    private static final double AXIS_DEPOSIT_CHARGES = 0.012;
    public static final double AXIS_MINIMUM_BALANCE = 500.0;

    private static final double INDIAN_WITHDRAW_CHARGES = 0.12;
    private static final double INDIAN_DEPOSIT_CHARGES = 0.015;
    public static final double INDIAN_MINIMUM_BALANCE = 600.0;

    private static final double SBI_WITHDRAW_CHARGES = 0.1;
    private static final double SBI_DEPOSIT_CHARGES = 0.01;
    public static final double SBI_MINIMUM_BALANCE = 1000.0;

    @Override
    public double getWithdrawCharges(String bankName) {
        switch (bankName.toLowerCase()) {
            case "axis":
                return AXIS_WITHDRAW_CHARGES;
            case "indian":
                return INDIAN_WITHDRAW_CHARGES;
            case "sbi":
                return SBI_WITHDRAW_CHARGES;
            default:
                return 0.0; 
        }
    }

    @Override
    public double getDepositCharges(String bankName) {
        switch (bankName.toLowerCase()) {
            case "axis":
                return AXIS_DEPOSIT_CHARGES;
            case "indian":
                return INDIAN_DEPOSIT_CHARGES;
            case "sbi":
                return SBI_DEPOSIT_CHARGES;
            default:
                return 0.0; // Return default value if bank not found
        }
    }

    @Override
    public double getMinimumBalance(String bankName) {
        switch (bankName.toLowerCase()) {
            case "axis":
                return AXIS_MINIMUM_BALANCE;
            case "indian":
                return INDIAN_MINIMUM_BALANCE;
            case "sbi":
                return SBI_MINIMUM_BALANCE;
            default:
                return 0.0; // Return default value if bank not found
        }
    }
}
