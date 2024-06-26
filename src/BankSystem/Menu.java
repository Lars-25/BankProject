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
            System.out.println("-------------------------------");
            System.out.println("Please select an option: ");
            System.out.println("1 - Show card details");
            System.out.println("2 - Deposit money");
            System.out.println("3 - Withdraw money");
            System.out.println("4 - Pay with credit card");
            System.out.println("5 - Pay credit card bill");
            System.out.println("6 - List cards");
            System.out.println("7 - Create Request");
            if (!client.getCardRequests().isEmpty()){
                System.out.println("8 - View Requests");
            }
            System.out.println("E - LogOut.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("****** Debit Card ******");
                    client.getDebitCard().showCard();
                    System.out.println("****** Credit Card ******");
                    for (CreditCard credit : client.getCreditCards()){
                        credit.showCard();
                    }
                    break;
                case "2":
                    if(client.getDebitCard().verifyClabe()){
                        try {
                            System.out.println("Enter the amount you wish to deposit: ");
                            int amount = scanner.nextInt();
                            if (amount > 0){
                                client.getDebitCard().depositMoney(amount);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter an integer");
                        }
                        System.out.println("Money successfully deposited");
                        scanner.nextLine();
                    }
                    break;
                case "3":
                    if(client.getDebitCard().verifyClabe()){
                        try {
                            System.out.println("Enter the amount you wish to withdraw: ");
                            int amount = scanner.nextInt();
                            client.getDebitCard().withdrawMoney(amount);
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter an integer");
                        }
                        System.out.println("Money successfully withdrawn");
                        scanner.nextLine();
                    }
                    break;
                case "4":
                    CreditCard credit = null;
                    if (client.getCreditCards().isEmpty()) break;
                    client.printCreditCardIndex();
                    try {
                        System.out.println("Enter the card you will pay with: ");
                        int cardIndex = scanner.nextInt();
                        if (cardIndex < client.getCreditCards().size() && cardIndex >= 0){
                            credit = client.getCreditCards().get(cardIndex);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("You must enter an integer");
                    }
                    assert credit != null;
                    if(credit.verifyClabe()){
                        try {
                            System.out.println("Enter the amount you will pay: ");
                            int amount = scanner.nextInt();
                            if (credit.getCredit() - amount >= 0){
                                credit.payWithCard(amount);
                            }
                            else{
                                System.out.println("Insufficient credit");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter an integer");
                        }
                        scanner.nextLine();
                    }
                    break;
                case "5":
                    if (client.getCreditCards().isEmpty()) break;
                    if(client.getDebitCard().verifyClabe()){
                        client.printCreditCardIndex();
                        try {
                            System.out.println("Enter the card to pay: ");
                            int cardIndex = scanner.nextInt();
                            if (cardIndex < client.getCreditCards().size() && cardIndex >= 0){
                                client.getCreditCards().get(cardIndex).payCard(client);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter an integer");
                        }
                    }
                    break;
                case "6":
                    client.showCards();
                    break;
                case "7":
                    client.requestCreditCard();
                    break;
                case "8":
                    client.viewCreditCardRequests();
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
            System.out.println("1 - Client options");
            System.out.println("2 - Account executive options");
            System.out.println("3 - Investor options");
            System.out.println("4 - Capturist options");
            System.out.println("5 - Review requests");
            System.out.println("6 - Show cards");
            System.out.println("7 - Show users");
            System.out.println("E - Log out.");
            option = scanner.nextLine();
            switch (option) {
                case "1": // Client
                    managerMenuClient();
                    break;
                case "2": // Executives
                    managerMenuExecutive();
                    break;
                case "3": // Investor
                    managerMenuInvestor();
                    break;
                case "4": // Capturist
                    managerMenuCapturist();
                    break;
                case "5":
                            boolean bandp = false;
                            if (users != null) {
                                for (User user : users) {
                                    Client client = (Client) user;
                                    if (client.getCardRequests() != null) {
                                        for (RequestCard request : client.getCardRequests()) {
                                            if (request.getStatus().equals("In process")) {
                                                bandp = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!bandp) {
                                System.out.println("No active requests");
                                executeManagerMenu();
                            } else {
                                boolean bandv = true;
                                Client finalClient = null;
                                int requestIndex = 0;
                                for (User user : users) {
                                    Client client = (Client) user;
                                    client.viewCreditCardRequests();
                                }
                                while (bandv) {
                                    System.out.println("Enter the username of the applicant: ");
                                    String username = scanner.nextLine();
                                    for (User user : users) {
                                        if (user.getUsername().equals(username)) {
                                            finalClient = (Client) user;
                                            bandv = false;
                                            break;
                                        }
                                    }
                                    if (bandv) {
                                        System.out.println("User entered not found");
                                    }
                                }
                                while (true) {
                                    RequestCard test = null;
                                    try {
                                        System.out.println("Enter the request number: ");
                                        requestIndex = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.println("You must enter an integer");
                                        scanner.nextLine();
                                        continue;
                                    }
                                    try {
                                        if (finalClient != null && finalClient.getCardRequests() != null && requestIndex >= 0 && requestIndex < finalClient.getCardRequests().size()) {
                                            test = finalClient.getCardRequests().get(requestIndex);
                                            if (!test.getStatus().equals("In process")) {
                                                test = null;
                                            }
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        System.out.println("Invalid index.");
                                    }
                                    if (test != null) {
                                        break;
                                    }
                                }
                                scanner.nextLine();
                                while (true) {
                                    try {
                                        System.out.println("What do you want to do with the request: [A] Approve [R] Reject");
                                        option = scanner.nextLine();
                                        if (option.equals("A")) {
                                            finalClient.getCardRequests().get(requestIndex).approveRequest();
                                            break;
                                        } else if (option.equals("R")) {
                                            finalClient.getCardRequests().get(requestIndex).rejectRequest();
                                            break;
                                        } else {
                                            System.out.println("Invalid option");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("An error occurred: " + e.getMessage());
                                    }
                                }
                            }
                    
                    break;
                case "6":
                if (users != null) {
                    for (User user : users) {
                        // Verificar si el usuario es una instancia de Client antes de intentar acceder a sus atributos específicos
                        if (user instanceof Client) {
                            Client client = (Client) user;
                            // Verificar si la tarjeta de débito de cliente no es nula antes de mostrarla
                            if (client.getDebitCard() != null) {
                                client.getDebitCard().showCard();
                            } else {
                                System.out.println("Debit card not available for user: " + client.getUsername());
                            }
                            // Verificar si hay tarjetas de crédito antes de mostrarlas
                            if (client.getCreditCards() != null) {
                                for (CreditCard credit : client.getCreditCards()) {
                                    // Verificar si la tarjeta de crédito no es nula antes de mostrarla
                                    if (credit != null) {
                                        credit.showCard();
                                    } else {
                                        System.out.println("Credit card not available for user: " + client.getUsername());
                                    }
                                }
                            } else {
                                System.out.println("No credit cards available for user: " + client.getUsername());
                            }
                        } else {
                            System.out.println("User is not a client: " + user.getUsername());
                        }
                    }
                } else {
                    System.out.println("No cards available.");
                }
                    break;
                case "7":
                    try {
                        User.showAllUsers();
                    } catch (Exception e) {
                        System.out.println("There is no users");
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

    private static void executeInvestorMenu() {
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the investor menu.");
        String option = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1 - Make investment to the bank");
            System.out.println("E - Log out");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    Investor.makeInvestment();
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
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.ACCOUNT_EXECUTIVE);
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the capturist menu.");
        do {
            System.out.println("1 - Register account executive");
            System.out.println("2 - Modify account executive information");
            System.out.println("3 - Delete account executive");
            System.out.println("4 - Show account executives");

            System.out.println("E - Log out.");
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
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CLIENT);
        System.out.println("Welcome " + UserInSession.getActualUser().getFirstName() + " you are in the account executive menu.");
        do {
            System.out.println("1 - Register client");
            System.out.println("2 - Modify client information");
            System.out.println("3 - Delete client");
            System.out.println("4 - Review requests");
            System.out.println("5 - Show clients");
            System.out.println("E - Log out.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    Client.registerClient();
                    System.out.println("Client successfully registered.");
                    break;
                case "2":
                    try {
                        Client.updateClientInformation(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                    break;
                case "3":
                    try {
                        Client.deleteClient(users, scanner);
                    } catch (Exception e) {
                        System.out.println("No Clients registered");
                    }
                        break;
                case "4":
                    boolean bandv = true;
                    Client finalClient = null;
                    int requestIndex = 0;
                    for (User user : users) {
                        Client client = (Client) user;
                        client.viewCreditCardRequests();
                    }
                    while (bandv) {
                        System.out.println("Enter the username: ");
                        String username = scanner.nextLine();
                        for (User user : users) {
                            if (user.getUsername().equals(username)) {
                                finalClient = (Client) user;
                                bandv = false;
                                break;
                            }
                        }
                        if (bandv) {
                            System.out.println("User entered not found");
                        }
                    }
                    while (true) {
                        try {
                            System.out.println("Enter the request number: ");
                            requestIndex = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter an integer");
                        }
                        if (requestIndex < 0 || requestIndex >= users.size()) {
                            System.out.println("Invalid index");
                        } else {
                            break;
                        }
                    }
                    scanner.nextLine();
                    while (true) {
                        System.out.println("What do you want to do with the request: [A] Approve [R] Reject");
                        option = scanner.nextLine();
                        if (option.equals("A")) {
                            finalClient.getCardRequests().get(requestIndex).approveRequest();
                            break;
                        } else if (option.equals("R")) {
                            finalClient.getCardRequests().get(requestIndex).rejectRequest();
                            break;
                        } else {
                            System.out.println("Invalid option");
                        }
                    }
                    break;
                case "5":
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
        System.out.println("****** Capturist options ******");
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
        boolean band = false, bandc = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.INVESTOR);
        System.out.println("****** Investor options ******");
        do {
            System.out.println("1 - Register investor");
            System.out.println("2 - Modify investor information");
            System.out.println("3 - Delete investor");
            System.out.println("4 - Show investor");

            System.out.println("E - Exit.");
            option = scanner.nextLine();
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
        System.out.println("****** Account executive options ******");
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
        Scanner scanner = new Scanner(System.in);

            ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CLIENT);

            System.out.println("****** Client options ******");
            String option = "";
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
    
