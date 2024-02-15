package com.onebill.bank.service;

import java.util.LinkedList;

import com.onebill.bank.data.user.AllUserBankData;

import java.util.Scanner;

public class UserService {

    static LinkedList<AllUserBankData> users;
    static Scanner scanner = new Scanner(System.in); // Declare the Scanner


    static {
        users = new LinkedList<>();
        users.add(new AllUserBankData("Krishna", "Krish@456", 10000, "SBI", "user"));
        users.add(new AllUserBankData("Nasim", "Nasim123", 8000, "SBI", "user"));
        users.add(new AllUserBankData("Roshan", "roshan@17", 5000, "AXIS", "user"));
        users.add(new AllUserBankData("Kiran", "kiran@111", 20000, "SBI", "user"));
        users.add(new AllUserBankData("admin", "admin", 0, "SBI", "admin"));
    }

    public static boolean checkIfUserExist(String userName) {
        for (AllUserBankData user : users) {
            if (user.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static AllUserBankData returnUserData(String userName) {
        for (AllUserBankData user : users) {
            boolean flag = checkIfUserExist(user.getName());
            if (flag) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(AllUserBankData user) {
        boolean flag = checkIfUserExist(user.getName());
        if (flag) {
            System.out.println("User already exists");
            return;
        }
        users.add(user);
        System.out.println("User added successfully.");
    }

    public static void removeUser(AllUserBankData user) {
        boolean flag = checkIfUserExist(user.getName());
        if (!flag) {
            System.out.println("User not found");
            return;
        }
        users.remove(user);
        System.out.println("User removed successfully.");
    }

    public void adminMenu(String enteredPassword) {
        if (enteredPassword.equals("admin")) {
            while (true) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Add User");
                System.out.println("2. Remove User");
                System.out.println("3. View All Users");
                System.out.println("4. Exit Admin Menu");
                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (adminChoice) {
                    case 1:
                        addUserFromAdmin(null);
                        break;
                    case 2:
                        removeUserFromAdmin(null);
                        break;
                    case 3:
                        viewAllUsers();
                        break;
                    case 4:
                        System.out.println("Exiting Admin Menu...");
                        return;
                    default:
                        System.err.println("Invalid choice. Please enter a valid option.");
                }
            }
        } else {
            System.err.println("Incorrect admin password. Exiting Admin Menu...");
        }
    }

    public static void addUserFromAdmin(Scanner scanner2) {
        // Prompt admin to enter user details
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        // Prompt admin to enter user bank name (excluding for admin user)
        String bankName = "";
        if (!newUsername.equalsIgnoreCase("admin")) {
            System.out.print("Enter bank name (Axis, Indian, or SBI): ");
            bankName = scanner.nextLine();
        }

        // Create new user and add to the list
        AllUserBankData newUser = new AllUserBankData(newUsername, newPassword, initialBalance, bankName, "user");
        addUser(newUser);
    }

    public static void removeUserFromAdmin(Scanner scanner2) {
        // Prompt admin to enter username to be removed
        System.out.print("Enter username to remove: ");
        String usernameToRemove = scanner.nextLine();

        // Find user in the list
        AllUserBankData userToRemove = returnUserData(usernameToRemove);

        // Remove user if found
        if (userToRemove != null) {
            removeUser(userToRemove);
        } else {
            System.out.println("User not found.");
        }
    }

    public static void viewAllUsers() {
        System.out.println("\nAll Users:");
        for (AllUserBankData user : users) {
            System.out.println("Username: " + user.getName());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Balance: " + user.getBalance());
            System.out.println("Bank Name: " + user.getBankName());
//            System.out.println("User Type: " + user.getUserType());
            System.out.println(); // Empty line between users
        }
    }
}
