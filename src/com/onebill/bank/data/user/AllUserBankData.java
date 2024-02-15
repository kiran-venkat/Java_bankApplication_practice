package com.onebill.bank.data.user;

import java.util.LinkedList;

public class AllUserBankData {

    private String name;
    private String password;
    private double balance;
    private String bankName;
    private String role;

	static LinkedList<AllUserBankData> users;
	static {
		users= new LinkedList<>();
		users.add(new AllUserBankData("Krishna","Krish@456",10000,"SBI","user"));
		users.add(new AllUserBankData("Nasim","Nasim123",8000,"SBI","user"));
		users.add(new AllUserBankData("Roshan","roshan@17",5000,"AXIS","user"));
		users.add(new AllUserBankData("Kiran","kiran@111",20000,"SBI","user"));
		users.add(new AllUserBankData("admin","admin",0,null,"admin"));
	}

    public AllUserBankData(String name, String password, double balance, String bankName,String role) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.bankName = bankName;
        this.setRole(role);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


     //Method to find user in dummy user data
	// Inside the AllUserBankData class
	public static AllUserBankData findUser(String username, String bankName) {
	    for (AllUserBankData user : users) {
	        // Check if the user is an admin (bankName is null)
	        if (user.getName().equalsIgnoreCase(username) && (user.getBankName() == null || user.getBankName().equalsIgnoreCase(bankName))) {
	            return user;
	        }
	    }
	    return null; // User not found
	}

}
