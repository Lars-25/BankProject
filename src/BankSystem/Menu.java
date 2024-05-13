package BankSystem;

import java.util.*;
import BankSystem.*;
import BankSystem.Utils.*;
import Users.*;
import Users.Utils.Role;
import Utils.*;
import Cards.*;
import Utils.UserInSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static BankSystem.Bank.users;
public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public Menu(){}

    public static void LogIn(){
        boolean correctData = true;

        System.out.println("****** WELCOME TO THE BANK SYSTEM ******");

        do {
            System.out.println("Log in to continue");

            System.out.println("Enter your username: ");
            String username = scanner.nextLine();

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            User user = bank.verifyLogIn(username, password);

            if (user != null){
                UserInSession.getInstance().setActualUser(user);
                selectMenu();
            } else {
                System.out.println("Account does not exist");
            }
        } while (correctData);
    }

    private static void selectMenu() {
        switch (UserInSession.getActualUser().getRole()) {
            case CLIENT -> executeClientMenu();
            case MANAGER -> executeManagerMenu();
            case INVESTOR -> executeInvestorMenu();
            case CAPTURIST -> executeCapturistMenu();
            case ACCOUNT_EXECUTIVE -> executeAccountExecutiveMenu();
        }
    }

    private static void executeClientMenu() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        Client client = (Client) UserInSession.getActualUser();
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + ", you are in the client menu.");
        do {
            System.out.println("-----------------------------------");
            System.out.println("Please select an option: ");
            System.out.println("1 - Show personal information");
            System.out.println("2 - Show card details");
            System.out.println("3 - Deposit money");
            System.out.println("4 - Withdraw money");
            System.out.println("5 - Pay with credit card");
            System.out.println("6 - Pay credit card");
            System.out.println("7- Create Request");
            if (!client.getCardRequests().isEmpty()){
                System.out.println("8 - View Requests");
            }
            System.out.println("E - LogOut.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    client.showPersonalInfo();
                case "2":
                    System.out.println("****** Debit Card ******");
                    client.getDebitCard().showCard();
                    System.out.println("****** Credit Card ******");
                    if (client.getCreditCards().isEmpty()) {
                        System.out.println("No credit cards registered.");
                    } else {
                        for (CreditCard creditCard : client.getCreditCards()) {
                            creditCard.showCard();
                        }
                    }
                    break;
                case "3":
                    if(client.getDebitCard().verifyClabe()){
                        try {
                            System.out.println("Enter the amount you wish to deposit: ");
                            int amount = scanner.nextInt();
                            if (amount > 0){
                                client.getDebitCard().depositMoney(amount);
                            }
                        } catch (Exception e) {
                            System.out.println("You must enter an integer");
                        }
                        System.out.println("Money successfully deposited");
                        scanner.nextLine();
                    }
                    break;
                case "4":
                    if(client.getDebitCard().verifyClabe()){
                        try {
                            System.out.println("Enter the amount you wish to withdraw: ");
                            int amount = scanner.nextInt();
                            client.getDebitCard().withdrawMoney(amount);
                        } catch (Exception e) {
                            System.out.println("You must enter an integer");
                        }
                        System.out.println("Money successfully withdrawn");
                        scanner.nextLine();
                    }
                    break;
                case "5":
                    if (client.getCreditCards().isEmpty()) {
                        System.out.println("No credit cards available.");
                        break;
                    }
                    client.printCreditCardIndex();
                    System.out.println("Select the index of the credit card you want to use:");
                    try {
                        int cardIndex = Integer.parseInt(scanner.nextLine());
                        if (cardIndex >= 0 && cardIndex < client.getCreditCards().size()) {
                            CreditCard creditCard = client.getCreditCards().get(cardIndex);
                            if (creditCard.verifyClabe()) {
                                System.out.println("Enter the payment amount:");
                                double amount = Double.parseDouble(scanner.nextLine());
                                creditCard.payWithCard(amount);
                            }
                        } else {
                            System.out.println("Invalid credit card index.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;
                case "6":
                    if (client.getCreditCards().isEmpty()) {
                        System.out.println("No credit cards to pay.");
                        break;
                    }
                    client.printCreditCardIndex();
                    System.out.println("Select the index of the credit card to pay off:");
                    try {
                        int cardIndex = Integer.parseInt(scanner.nextLine());
                        if (cardIndex >= 0 && cardIndex < client.getCreditCards().size()) {
                            CreditCard creditCard = client.getCreditCards().get(cardIndex);
                            creditCard.payCard(client);
                        } else {
                            System.out.println("Invalid credit card index.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;
                case "7":
                    client.requestCreditCard();
                    break;
                case "8":
                    client.viewCreditCardRequestsClient();
                    break;
                case "E":
                    UserInSession.getInstance().logOut();
                    LogIn();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }

    private static void executeManagerMenu() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CLIENT);
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the manager menu.");

        do {
            System.out.println("-----------------------------------");
            System.out.println("1 - Client options");
            System.out.println("2 - Client options");
            System.out.println("3 - Account executive options");
            System.out.println("4 - Investor options");
            System.out.println("5 - Capturist options");
            System.out.println("6 - Review requests");
            System.out.println("7 - Show users");
            System.out.println("E - Log out.");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    Manager.showInfoAllManagers();
                    break;
                case "2":
                    managerMenuClient();
                    break;
                case "3":
                    managerMenuExecutive();
                    break;
                case "4":
                    managerMenuInvestor();
                    break;
                case "5":
                    managerMenuCapturist();
                    break;
                case "6":
                    boolean hasActiveRequests = false;
                    for (User user : users) {
                        if (user instanceof Client) {
                            Client client = (Client) user;
                            for (RequestCard request : client.getCardRequests()) {
                                if (request.getStatus().equals("In process")) {
                                    hasActiveRequests = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!hasActiveRequests) {
                        System.out.println("No active requests");
                    } else {
                        for (User user : users) {
                            if (user instanceof Client) {
                                Client client = (Client) user;
                                client.viewCreditCardRequestsInProcess();
                            }
                        }
                        Client finalClient = null;
                        int requestIndex = 0;
                        while (finalClient == null) {
                            System.out.println("Enter the username of the applicant or type 'exit' to cancel:");
                            String name = scanner.nextLine();
                            if ("exit".equalsIgnoreCase(name)) break;
                            for (User user : users) {
                                if (user.getUsername().equals(name) && user instanceof Client) {
                                    finalClient = (Client) user;
                                    break;
                                }
                            }
                            if (finalClient == null) {
                                System.out.println("User not found, try again.");
                            }
                        }

                        if (finalClient != null) {
                            RequestCard testRequest = null;
                            while (testRequest == null) {
                                System.out.println("Enter the request number:");
                                try {
                                    requestIndex = scanner.nextInt();
                                    scanner.nextLine();  // Clear buffer
                                    testRequest = finalClient.getCardRequests().get(requestIndex);
                                    if (!"In process".equals(testRequest.getStatus())) {
                                        System.out.println("Request not in process, choose another.");
                                        testRequest = null;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Please enter an integer.");
                                    scanner.nextLine();
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Invalid index, please try again.");
                                }
                            }

                            while (true) {
                                System.out.println("Request active: [A] Approve, [R] Reject, [C] Cancel");
                                String option_request = scanner.nextLine().trim().toUpperCase();
                                switch (option_request) {
                                    case "A":
                                        testRequest.approveRequest();
                                        System.out.println("Request approved successfully.");
                                        return;
                                    case "R":
                                        testRequest.rejectRequest();
                                        System.out.println("Request rejected successfully.");
                                        return;
                                    case "C":
                                        System.out.println("No action taken, returning to menu.");
                                        return;
                                    default:
                                        System.out.println("Invalid option, please enter 'A' to approve, 'R' to reject, or 'C' to cancel.");
                                }
                            }
                        }
                    }
                    break;
                case "7":
                    try {
                        User.showAllUsers();
                    } catch (Exception e) {
                        System.out.println("There are no users.");
                    }
                    break;
                case "E":
                    UserInSession.getInstance().logOut();
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!"E".equalsIgnoreCase(option));
    }

    private static void executeInvestorMenu() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        Investor investor = (Investor) UserInSession.getActualUser();
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the investor menu.");
        do {
            System.out.println("-----------------------------------");
            System.out.println("1 - Show personal information");
            System.out.println("2 - Make investment to the bank");
            System.out.println("3 - Show Investor specific details:");
            System.out.println("E - Log out");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    investor.showPersonalInfo();
                    break;
                case "2":
                    Investor.makeInvestment();
                    break;
                case "3":
                    Investor.showInvestmentDetails();
                    break;
                case "E":
                    UserInSession.getInstance().logOut();
                    LogIn();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }

    private static void executeCapturistMenu() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        Capturist capturist = (Capturist) UserInSession.getActualUser();
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.ACCOUNT_EXECUTIVE);
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the capturist menu.");
        do {
            System.out.println("-----------------------------------");
            System.out.println("1 - Show personal information");
            System.out.println("2 - Register account executive");
            System.out.println("3 - Modify account executive information");
            System.out.println("4 - Delete account executive");
            System.out.println("5 - Show account executives");
            System.out.println("E - Log out.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    capturist.showPersonalInfo();
                    break;
                case "2":
                    AccountExecutive.registerAccountExecutive();
                    System.out.println("Account Executive successfully registered.");
                    break;
                case "3":
                        try {
                            AccountExecutive.updateAccountExecutiveInformation(users, scanner);
                        } catch (Exception e) {
                            System.out.println("No Account Executives registered");
                        }
                    break;
                case "4":
                    try {
                        AccountExecutive.deleteAccountExecutive(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Account Executives registered");
                    }
                    break;
                case "5":
                    try{
                        AccountExecutive.showInfoAllAccountExecutives();
                    } catch (Exception e){
                        System.out.println("No Account Executives registered");
                    }
                    break;
                case "E":
                    UserInSession.getInstance().logOut();
                    LogIn();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }

    private static void executeAccountExecutiveMenu() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        AccountExecutive accountExecutive = (AccountExecutive) UserInSession.getActualUser();
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CLIENT);
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the account executive menu.");
        do {
            System.out.println("-----------------------------------");
            System.out.println("1 - Show personal information");
            System.out.println("2 - Register client");
            System.out.println("3 - Modify client information");
            System.out.println("4 - Delete client");
            System.out.println("5 - Review requests");
            System.out.println("6 - Show clients");
            System.out.println("E - Log out.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    accountExecutive.showPersonalInfo();
                    break;
                case "2":
                    Client.registerClient();
                    System.out.println("Client successfully registered.");
                    break;
                case "3":
                    try {
                        Client.updateClientInformation(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                    break;
                case "4":
                    try {
                        Client.deleteClient(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                        break;
                case "5":
                    boolean isValidUser = true;
                    Client finalClient = null;
                    int requestIndex = 0;

                    for (User user : users) {
                        if (user instanceof Client) {
                            Client client = (Client) user;
                            client.viewCreditCardRequestsInProcess();
                        }
                    }
                    while (isValidUser) {
                        System.out.println("Enter the Client Name: ");
                        String name = scanner.nextLine();
                        for (User user : users) {
                            if (user.getUsername().equals(name)) {
                                finalClient = (Client) user;
                                isValidUser = false;
                                break;
                            }
                        }
                        if (isValidUser) {
                            System.out.println("Client not found.");
                        }
                    }
                    while (true) {
                        try {
                            System.out.println("Enter the request number: ");
                            requestIndex = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer.");
                            scanner.nextLine();
                            continue;
                        }
                        if (requestIndex < 0 || requestIndex >= users.size()) {
                            System.out.println("Invalid index.");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("Request active: [A] Approve [R] Reject");
                        String option_request = scanner.nextLine().trim().toUpperCase();
                        switch (option_request) {
                            case "A":
                                finalClient.getCardRequests().get(requestIndex).approveRequest();
                                System.out.println("Request approved successfully.");
                                return;
                            case "R":
                                finalClient.getCardRequests().get(requestIndex).rejectRequest();
                                System.out.println("Request rejected successfully.");
                                return;
                            default:
                                System.out.println("Invalid option, please enter 'A' to approve or 'R' to reject.");
                        }
                    }
                case "6":
                    try{
                        Client.showInfoAllClients();
                    }catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                    break;
                case "E":
                    UserInSession.getInstance().logOut();
                    LogIn();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));

    }

    private static void managerMenuCapturist() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CAPTURIST);
        System.out.println("-------- Capturist options --------");
        do {
            System.out.println("1 - Register capturist");
            System.out.println("2 - Modify capturist information");
            System.out.println("3 - Delete capturist");
            System.out.println("4 - Show capturists");
            System.out.println("E - Exit.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    Capturist.registerCapturist();
                    System.out.println("Capturist successfully registered.");
                    break;
                case "2":
                    try {
                        Capturist.updateCapturistInformation(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Capturist registered");
                    }
                    break;
                case "3":
                    try {
                        Capturist.deleteCapturist(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Capturist registered");
                    }
                    break;
                case "4":
                    try{
                        Capturist.showInfoAllCapturists();
                    }catch (Exception e) {
                    System.out.println("No Capturist registered");
                }
                    break;
                case "E":
                    executeManagerMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }

    private static void managerMenuInvestor() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.INVESTOR);
        System.out.println("-------- Investor options --------");
        do {
            System.out.println("1 - Register investor");
            System.out.println("2 - Modify investor information");
            System.out.println("3 - Delete investor");
            System.out.println("4 - Show investor");
            System.out.println("E - Exit.");
            option = scanner.nextLine();
            boolean band = false, bandc = false;
            switch (option) {
                case "1":
                    band = managerPassword();
                    if (band) {
                        Investor.registerInvestor();
                        System.out.println("Investor successfully registered.");
                    } else {
                        System.out.println("Incorrect password");
                    }
                    break;
                case "2":
                    try{
                        Investor.updateInvestorInformation(users, scanner);
                    }catch (Exception e) {
                        System.out.println("No Investors registered");
                    }
                    break;
                case "3":
                    try{
                        Investor.deleteInvestor(users, scanner);
                    }catch (Exception e) {
                        System.out.println("No Investors registered");
                    }
                    break;
                case "4":
                    band = managerPassword();
                    if (band) {
                        try {
                            Investor.showInfoAllInvestors();
                        }catch (Exception e) {
                            System.out.println("No Investors registered");
                        }
                    } else {
                        System.out.println("Incorrect password");
                    }
                    break;
                case "E":
                    executeManagerMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }

    public static boolean managerPassword() {
        System.out.println("For added security, please enter your password: ");
        String password = scanner.nextLine();
        if (UserInSession.getActualUser().getPassword().equals(password)) {
            System.out.println("Correct password, proceed with the procedure.");
            return true;
        } else {
            return false;
        }
    }

    private static void managerMenuExecutive() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.ACCOUNT_EXECUTIVE);
        System.out.println("-------- Account Executive options --------");
        do {
            System.out.println("1 - Register account executive");
            System.out.println("2 - Modify account executive information");
            System.out.println("3 - Delete account executive");
            System.out.println("4 - Show account executives");
            System.out.println("E - Exit.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    AccountExecutive.registerAccountExecutive();
                    System.out.println("Account Executive successfully registered.");
                    break;
                case "2":
                    try {
                        AccountExecutive.updateAccountExecutiveInformation(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Account Executives registered");
                    }
                    break;
                case "3":
                    try {
                        AccountExecutive.deleteAccountExecutive(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Account Executives registered");
                    }
                    break;
                case "4":
                    try {
                        AccountExecutive.showInfoAllAccountExecutives();
                    } catch (Exception e) {
                        System.out.println("No Account Executives registered");
                    }
                    break;
                case "E":
                    executeManagerMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }

    private static void managerMenuClient() {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CLIENT);
        System.out.println("-------- Client options --------");
        do {
            System.out.println("1 - Register client");
            System.out.println("2 - Modify client information");
            System.out.println("3 - Delete client");
            System.out.println("4 - Show clients");
            System.out.println("E - Exit.");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    Client.registerClient();
                    System.out.println("Client successfully registered.");
                    break;
                case "2":
                    try{
                        Client.updateClientInformation(users, scanner);
                    }catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                    break;
                case "3":
                    try{
                        Client.deleteClient(users, scanner);
                    }catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                    break;
                case "4":
                    try{
                        Client.showInfoAllClients();
                    }catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                    break;
                case "E":
                    executeManagerMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!option.equals("E"));
    }
    

    public static void predefinedRegistration() {
        users.put(BranchOfficeRole.MADERO, new HashMap<>());
        users.put(BranchOfficeRole.ACUEDUCTO, new HashMap<>());


        // Clients
        users.get(BranchOfficeRole.ACUEDUCTO).put(Role.CLIENT, new ArrayList<>());
        Client newClientAcueducto = new Client("CLIENT", "ACUEDUCTO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "CLIENT_ACUEDUCTO", BranchOfficeRole.ACUEDUCTO);
        users.get(BranchOfficeRole.ACUEDUCTO).get(Role.CLIENT).add(newClientAcueducto);

        users.get(BranchOfficeRole.MADERO).put(Role.CLIENT, new ArrayList<>());
        Client newClientMadero = new Client("CLIENT", "MADERO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "CLIENT_MADERO", BranchOfficeRole.MADERO);
        users.get(BranchOfficeRole.MADERO).get(Role.CLIENT).add(newClientMadero);

        // Managers
        users.get(BranchOfficeRole.ACUEDUCTO).put(Role.MANAGER, new ArrayList<>());
        Manager newManagerAcueducto = new Manager("MANAGER", "ACUEDUCTO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "MANAGER_ACUEDUCTO", BranchOfficeRole.ACUEDUCTO, 50000, LocalDate.now());
        users.get(BranchOfficeRole.ACUEDUCTO).get(Role.MANAGER).add(newManagerAcueducto);

        users.get(BranchOfficeRole.MADERO).put(Role.MANAGER, new ArrayList<>());
        Manager newManagerMadero = new Manager("MANAGER", "MADERO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "MANAGER_MADERO", BranchOfficeRole.MADERO, 50000, LocalDate.now());
        users.get(BranchOfficeRole.MADERO).get(Role.MANAGER).add(newManagerMadero);

        // Account Executives
        users.get(BranchOfficeRole.ACUEDUCTO).put(Role.ACCOUNT_EXECUTIVE, new ArrayList<>());
        AccountExecutive newExecutiveAcueducto = new AccountExecutive("EXECUTIVE", "ACUEDUCTO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "EXECUTIVE_ACUEDUCTO", BranchOfficeRole.ACUEDUCTO, 50000, LocalDate.now());
        users.get(BranchOfficeRole.ACUEDUCTO).get(Role.ACCOUNT_EXECUTIVE).add(newExecutiveAcueducto);

        users.get(BranchOfficeRole.MADERO).put(Role.ACCOUNT_EXECUTIVE, new ArrayList<>());
        AccountExecutive newExecutiveMadero = new AccountExecutive("EXECUTIVE", "MADERO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "EXECUTIVE_MADERO", BranchOfficeRole.MADERO, 50000, LocalDate.now());
        users.get(BranchOfficeRole.MADERO).get(Role.ACCOUNT_EXECUTIVE).add(newExecutiveMadero);

        // Investors
        users.get(BranchOfficeRole.ACUEDUCTO).put(Role.INVESTOR, new ArrayList<>());
        Investor newInvestorAcueducto = new Investor("INVESTOR", "ACUEDUCTO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "INVESTOR_ACUEDUCTO", BranchOfficeRole.ACUEDUCTO);
        users.get(BranchOfficeRole.ACUEDUCTO).get(Role.INVESTOR).add(newInvestorAcueducto);

        users.get(BranchOfficeRole.MADERO).put(Role.INVESTOR, new ArrayList<>());
        Investor newInvestorMadero = new Investor("INVESTOR", "MADERO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "INVESTOR_MADERO", BranchOfficeRole.MADERO);
        users.get(BranchOfficeRole.MADERO).get(Role.INVESTOR).add(newInvestorMadero);

        // Capturists
        users.get(BranchOfficeRole.ACUEDUCTO).put(Role.CAPTURIST, new ArrayList<>());
        Capturist newCapturistAcueducto = new Capturist("CAPTURIS", "ACUEDUCTO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "CAPTURIS_ACUEDUCTO", BranchOfficeRole.ACUEDUCTO, 50000, LocalDate.now());
        users.get(BranchOfficeRole.ACUEDUCTO).get(Role.CAPTURIST).add(newCapturistAcueducto);

        users.get(BranchOfficeRole.MADERO).put(Role.CAPTURIST, new ArrayList<>());
        Capturist newCapturistMadero = new Capturist("CAPTURIST", "MADERO", LocalDate.of(1900, 10, 10), "CITY", "COUNTRY", "RFC", "CURP", "ADDRESS", "PASSWORD", "CAPTURIST_MADERO", BranchOfficeRole.MADERO, 50000, LocalDate.now());
        users.get(BranchOfficeRole.MADERO).get(Role.CAPTURIST).add(newCapturistMadero);

    }
}
    
