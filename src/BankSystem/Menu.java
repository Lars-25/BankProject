package BankSystem;

import java.util.*;
import BankSystem.Utils.*;
import Persons.*;
import Utils.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static HashMap<BranchOfficeRole,Bank> branchOffices=new HashMap<>();

    public static void executeMenu(){
        BranchOfficeRole branchOfficeRole = null;
        boolean flag = true;
        do {

            if (branchOfficeRole == null) {
                branchOfficeRole = askBranchOffice();
                ActualBranchOffice.getInstance().setBracnhOffice(branchOfficeRole);
            }

            logIn();
            
            System.out.printf("\nYou are currently in the Branch Office: %s", ActualBranchOffice.getInstance().getActualBranchOffice().toString());
            System.out.println("1. Stay in this branch");
            System.out.println("2. Back to Main Menu");
            int answer = Asks.forInt("The option number");
            
            if (answer != 1) {
                flag = false;
                ActualBranchOffice.getInstance().closeBranchOffice();
            }
        } while (flag);
    }

    //Diferentes menus por el rol

    public static void ClientMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (CLIENT)-----");
        while (true) {                       
            System.out.println("1. Check personal information");
            System.out.println("2. Modify personal information");
            System.out.println("3. Make a deposit to a card");
            System.out.println("4. Withdraw money from a card");
            System.out.println("5. Check information about my cards");
            System.out.println("6. Request a credit card");
            System.out.println("7. Log out");
            int option = Asks.forInt("the option number ");

            switch (option) {
                case 1 -> System.out.println(PersonInSession.getInstance().getActualPerson());
                case 2 -> Client.modifyPersonalInfo();//Falta hacer los metodos
                case 3 -> Client.deposit();
                case 4 -> Client.withdraw();
                case 5 -> Client.consultCardInfo();
                case 6 -> Client.requestCard();
                case 7 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
    }

    public static void CapturistMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (CAPTURIST)-----");
        while (true) {
            System.out.println("1. Register Account Executive");
            System.out.println("2. Modify Account Executive information");
            System.out.println("3. Delete Account Executive");
            System.out.println("4. Show Account Executive");
            System.out.println("5. Log out");
            int option = Asks.forInt("the option number");

            switch (option) {
                case 1 -> Capturist.registerAccountExecutive();//Falta hacer los metodos
                case 2 -> Capturist.modifyAccountExecutive();
                case 3 -> Capturist.deleteAccountExecutive();
                case 4 -> Capturist.searchAccountExecutive();
                case 5 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
    }

    public static void AccountExecutiveMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (ACCOUNT EXECUTIVE)-----");
        while (true) {
            System.out.println("1. Register client");
            System.out.println("2. Modify client information");
            System.out.println("3. Delete a client");
            System.out.println("4. Show clients");
            System.out.println("5. Show personal information of a client");
            System.out.println("6. Authorize card");
            System.out.println("7. Deny card");
            System.out.println("8. Log out");
            int option = Asks.forInt("The option number");

            switch (option) {
                case 1 -> AccountExecutive.registerClient();//Falta hacer los metodos
                case 2 -> AccountExecutive.modifyClient();
                case 3 -> AccountExecutive.deleteClient();
                case 4 -> AccountExecutive.searchClient();
                case 5 -> AccountExecutive.showInfoClient();
                case 6 -> AccountExecutive.authorizeCard();
                case 7 -> AccountExecutive.denyCard();
                case 8 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
    }

    public static void InvestorMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (INVESTOR)-----");
        while (true) {
            System.out.println("1. Check my personal information");
            System.out.println("2. Make an investment with the bank");
            System.out.println("3. Log out");
            int option = Asks.forInt("the option number");
        
            switch (option) {
                case 1 -> System.out.println(PersonInSession.getInstance().getActualPerson());
                case 2 -> Investor.makeInvestment();//Falta hacer los metodos
                case 3 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
        
    }

    public static void ManagerMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (MANAGER)-----");
        while (true) {
            System.out.println("1. Register user");
            System.out.println("2. Delete user");
            System.out.println("3. Modify user");
            System.out.println("4. Consult information about a specific user");
            System.out.println("5. Consult the information of all users (You must indicate the type)");
            System.out.println("6. Consult information about Investor Investment");
            System.out.println("7. Consult information about Capturist Movements");
            System.out.println("8. View credit card requests");
            System.out.println("9. Log out");
            int option = Asks.forInt("The option number");
            switch (option) {
                case 1 -> {
                    switch (askUserType()) {
                        case CLIENT -> Manager.registerClient();//Falta hacer los metodos
                        case CAPTURIST -> Manager.registerCapturist();
                        case ACCOUNT_EXECUTIVE -> Manager.registerAccountExecutive();
                        case INVESTOR -> Manager.registerInvestor();
                        default -> System.out.println("An invalid option was entered, please try again");
                    }
                }

                case 2 -> {
                    switch (askUserType()) {
                        case CLIENT -> Manager.deleteClient();
                        case CAPTURIST -> Manager.deleteCapturist();
                        case ACCOUNT_EXECUTIVE -> Manager.deleteAccountExecutive();
                        case INVESTOR -> Manager.deleteInvestor();
                        default -> System.out.println("An invalid option was entered, please try again");
                    }
                }

                case 3 -> {
                    switch (askUserType()) {
                        case CLIENT -> Manager.modifyClient();
                        case CAPTURIST -> Manager.modifyCapturist();
                        case ACCOUNT_EXECUTIVE -> Manager.modifyAccountExecutive();
                        case INVESTOR -> Manager.modifyInvestor();
                        default -> System.out.println("An invalid option was entered, please try again");
                    }
                }

                case 4 -> {
                    switch (askUserType()) {
                        case CLIENT -> Manager.showInfoClient();
                        case CAPTURIST -> Manager.showInfoCapturist();
                        case ACCOUNT_EXECUTIVE -> Manager.showInfoAccountExecutive();
                        case INVESTOR -> Manager.showInfoInvestor();
                        default -> System.out.println("An invalid option was entered, please try again");
                    }
                }

                case 5 -> {
                    switch (askUserType()) {
                        case CLIENT -> Manager.showInfoAllClients();
                        case CAPTURIST -> Manager.showInfoAllCapturists();
                        case ACCOUNT_EXECUTIVE -> Manager.showInfoAllAccountExecutives();
                        case INVESTOR -> Manager.showInfoAllInvestors();
                        default -> System.out.println("An invalid option was entered, please try again");
                    }
                }

                case 6 -> Manager.viewInvestorInvestment();
                case 7 -> Manager.viewCapturistMovement();
                case 8 -> Manager.viewCardRequest();
                case 9 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
    }

    
    //Iniciar sesion
    private static void logIn() {
        boolean correctData = false;
        System.out.println("------------LOG IN ------------");
        do {
            String username = Asks.forString("username");
            String password = Asks.forString("password");

            User person = branchOffices.get(ActualBranchOffice.getInstance().getActualBranchOffice()).verifyLogin(username, password);
            if (person != null) {
                PersonInSession.getInstance().setPerson(person);
                correctData = true;
                selectMenu();
            } else {
                System.out.println("Username or password wrong, Try again.");
            }
        } while (!correctData);
    }


    //Metodos auxiliares (Select Menu, askUserType, askBranchOffice)

    public static void selectMenu() {
        switch (PersonInSession.getInstance().getActualPerson().getRole()) {
            case CLIENT ->ClientMenu();
            case CAPTURIST -> CapturistMenu();
            case ACCOUNT_EXECUTIVE -> AccountExecutiveMenu();
            case INVESTOR -> InvestorMenu();
            case MANAGER -> ManagerMenu();
        }
    }

    public static Role askUserType() {
        Role role = null;
        while (role == null) {
            System.out.println("Enter the user type");
            System.out.println("1. Client");
            System.out.println("2. AccountExecutive");
            System.out.println("3. Capturist");
            System.out.println("4. Investor");
            int answer = Asks.forInt("The number option");

            switch (answer) {
                case 1 -> role = Role.CLIENT;
                case 2 -> role = Role.ACCOUNT_EXECUTIVE;
                case 3 -> role = Role.CAPTURIST;
                case 4 -> role = Role.INVESTOR;
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
        return role;
    }

    public static BranchOfficeRole askBranchOffice(){
        while (true) {
            
            System.out.println("Please choose your BranchOffice");
            System.out.println("1. Madero");
            System.out.println("2. Acueducto");
            System.out.println("3. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1)
            return BranchOfficeRole.MADERO;
        else if (option == 2)
            return BranchOfficeRole.ACUEDUCTO;
        else
            System.out.println("An invalid option was entered, please try again");

        }
    }



}
