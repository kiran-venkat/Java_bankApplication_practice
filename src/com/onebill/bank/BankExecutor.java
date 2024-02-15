package com.onebill.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import com.onebill.bank.data.user.AllUserBankData;
import com.onebill.bank.service.BankService;
import com.onebill.bank.data.bank.HelpSupport;
import com.onebill.bank.service.UserService;

public class BankExecutor {

    private static ArrayList<HelpSupport> helpSupportList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Help and Support");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int mainMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (mainMenuChoice == 1) {
                // User selected login
                loginMenu(scanner);
            } else if (mainMenuChoice == 2) {
                // User selected Help and Support
                displayHelpSupportMenu(scanner, null);
            } else if (mainMenuChoice == 3) {
                System.out.println("Exiting...");
                break; // Break out of the main loop
            } else {
                System.err.println("Invalid choice. Please try again.");
            }
        }

        // Close the scanner outside the loop
        scanner.close();
    }

    private static void loginMenu(Scanner scanner) {
        // Display prompt to select a user
        System.out.print("Enter your username:");
        String enteredUsername = scanner.nextLine();

        // Prompt user to enter bank name
        System.out.print("Enter your bank name (Axis, Indian, or SBI):");
        String bankName = scanner.nextLine();

        // Check if the entered username exists
        AllUserBankData selectedUser = AllUserBankData.findUser(enteredUsername, bankName);
        if (selectedUser == null) {
            System.err.println("User not found. Please try again.");
            return; // Return to the main menu
        }

        // Validate user password
        System.out.print("Enter password for " + selectedUser.getName() + ": ");
        String enteredPassword = scanner.nextLine();

        if (!enteredPassword.equals(selectedUser.getPassword())) {
            System.err.println("Incorrect password. Please try again.");
            return; // Return to the main menu
        }

        System.out.println();
        System.out.println(
                "Welcome to " + selectedUser.getBankName() + " bank services, " + selectedUser.getName());

        // Create BankService object with user data and bank name
        BankService bankService = new BankService(selectedUser, bankName);

        // Menu-driven code
        while (true) {
            System.out.println("\nMenu:");

            if (selectedUser.getRole().equalsIgnoreCase("admin")) {
                // Admin-specific menu
                System.out.println("1. Add User");
                System.out.println("2. Remove User");
                System.out.println("3. View All Users");
                System.out.println("4. View All Complaints");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (adminChoice) {
                    case 1:
                        UserService.addUserFromAdmin(scanner);
                        break;
                    case 2:
                        UserService.removeUserFromAdmin(scanner);
                        break;
                    case 3:
                        UserService.viewAllUsers();
                        break;
                    case 4:
                        viewAllComplaints();
                        break;
                    case 5:
                        System.out.println("Exiting admin menu...");
                        return; // Return to the login menu
                    default:
                        System.err.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } else {
                // Regular user menu
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Show Balance");
                System.out.println("4. Help and Support");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (userChoice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        bankService.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        bankService.withdraw(withdrawAmount);
                        break;
                    case 3:
                        bankService.showBalance();
                        break;
                    case 4:
                        displayHelpSupportMenu(scanner, enteredUsername);
                        break;
                        
                    case 5:
                        System.out.println("Exiting user menu...");
                        return; // Return to the login menu
                    default:
                        System.err.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        }
    }

    private static void displayHelpSupportMenu(Scanner scanner, String enteredUsername) {
        System.out.println("Help and Support Menu:");
        System.out.println("1. Username related issue");
        System.out.println("2. Password related issue");
        System.out.println("3. Others");

        System.out.print("Enter your choice: ");
        int helpSupportChoice = scanner.nextInt();
        scanner.nextLine();

        String complaint;
        switch (helpSupportChoice) {
            case 1:
                complaint = "Username related issue";
                break;
            case 2:
                complaint = "Password related issue";
                break;
            case 3:
                System.out.print("Enter your complaint: ");
                complaint = scanner.nextLine();
                break;
            default:
                System.err.println("Invalid choice. Exiting Help and Support.");
                return;
        }

        // Create HelpSupport object and add it to the list
        HelpSupport helpSupport = new HelpSupport(enteredUsername, complaint, new Date());
        helpSupportList.add(helpSupport);

        System.out.println("Your complaint has been submitted. Thank you for reaching out!");
    }

    private static void viewAllComplaints() {
        System.out.println("\nAll Complaints:");
        if (helpSupportList.isEmpty()) {
            System.out.println("No complaints registered.");
        } else {
            for (HelpSupport helpSupport : helpSupportList) {
                System.out.println("Username: " + helpSupport.getUserName());
                System.out.println("Complaint: " + helpSupport.getComplaint());
                System.out.println("Date: " + helpSupport.getDate());
                System.out.println("-----------------------------");
            }
        }
    }
}
