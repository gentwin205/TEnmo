package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        AccountService accountService = new AccountService(API_BASE_URL, currentUser);
        try{
            accountService.newBalance();
        }catch(NullPointerException e){
            System.out.println("no balance");
        }
		
	}

	private void viewTransferHistory() {
		//Display full list of transfers
        TransferService transferService = new TransferService(API_BASE_URL, currentUser);
        Transfer[] transfers = transferService.listTransfers(currentUser.getUser().getId());
        consoleService.displayTransfers(transfers, currentUser.getUser().getUsername());

        //prompt for transfer ID to see details
        int transferId = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
        if (transferId == 0) {
            return;
        }

        //display details
        Transfer t = transferService.getTransferById(transferId);
        consoleService.displayTransferDetails(t);
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {

        TransferService transferService = new TransferService(API_BASE_URL, currentUser);
        User[] users = transferService.getUsers();

        consoleService.displayUsers(users);
        int senderId = currentUser.getUser().getId();

        int recipientId = consoleService.promptForInt("Enter ID of user you are sending to (0 to cancel): ");

        if (recipientId == 0){
            return;
        } else if(senderId == recipientId) {
            System.out.println("You can't send money to yourself.");
            return;
        }

        BigDecimal amountGiven = consoleService.promptForBigDecimal("Enter amount: ");

        if(amountGiven.doubleValue() <= 0) {
            System.out.println("Enter a positive amount.");
            return;
        }

        transferService.transferBucks(senderId, recipientId, amountGiven);

	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
