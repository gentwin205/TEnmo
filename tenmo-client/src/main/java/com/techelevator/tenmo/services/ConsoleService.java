package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void displayUsers(User[] users){
        System.out.println("-------------------------------------------");
        System.out.println("Users");
        System.out.printf("%s    %s\n", "ID", "Username");
        for (User a : users){
            String name = a.getUsername();
            int id = a.getId();
            System.out.printf("%s    %s\n", id, name);
        }
        System.out.println("-------------------------------------------");
    }

    public void displayTransfers(Transfer[] transfers, String username) {
        System.out.println("------------------------------------------------------");
        System.out.println("Transfers");
        System.out.printf("%-20s%-20s%-20s\n", "ID", "From/To", "Amount");
        System.out.println("------------------------------------------------------");

        for(Transfer t : transfers) {
            System.out.printf("%-20s%s%-20s$%-20f\n",
                    t.getTransferId(),
                    (t.getUserFrom().equals(username) ? "To: " : "From: "),
                    (t.getUserFrom().equals(username) ? t.getUserTo() : t.getUserFrom()),
                    t.getAmount().doubleValue());
        }

        System.out.println("------------------------------------------------------");
    }

    public void displayTransferDetails(Transfer t) {
        System.out.println("------------------------------------------------------");
        System.out.println("Transfer Details");
        System.out.println("------------------------------------------------------");
        System.out.printf("Id: %d\n", t.getTransferId());
        System.out.printf("From: %s\n", t.getUserFrom());
        System.out.printf("To: %s\n", t.getUserTo());
        System.out.printf("Type: %s\n", t.getTransferTypeDesc());
        System.out.printf("Status: %s\n", t.getTransferStatusDesc());
        System.out.printf("Amount: $%f\n", t.getAmount().doubleValue());

    }
}
