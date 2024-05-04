package BankSystem;

import java.util.*;

import BankSystem.Utils.BranchOfficeRole;
import BankSystem.Utils.Role;
import Persons.AccountExecutive;
import Persons.Capturist;
import Persons.Client;
import Persons.Investor;
import Persons.Manager;
import Persons.Person;
import Utils.ActualBranchOffice;
import Utils.Asks;
import Utils.PersonInSession;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static HashMap<BranchOfficeRole,Bank> branchOffices=new HashMap<>();

    //Ejecutar menu/programa principal
    public static void executeMenu(){
        BranchOfficeRole branchOfficeRole = null;
        boolean flag = true;
        do {

            if (branchOfficeRole == null) {
                branchOfficeRole = askBranchOffice();
                ActualBranchOffice.getInstance().setSucursal(branchOfficeRole);
            }

            logIn();
            
            System.out.printf("\nYou are currently in the branch: %s", ActualBranchOffice.getInstance().getActualBranchOffice().toString());
            System.out.println("1. Stay in this branch");
            System.out.println("2. Back to Main Menu");
            int answer = Asks.forInt("the option number");
            
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

    public static void AccountExecutiveMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (Account Executive)-----");
        while (true) {
            System.out.println("1. Consult my personal information");
            System.out.println("2. Modify my personal information");
            System.out.println("3. Register client");
            System.out.println("4. Delete client");
            System.out.println("5. Modify client");
            System.out.println("6. Consult information about a specific user");
            System.out.println("7. Consult the information of all users (You must indicate the type)");
            System.out.println("8. View credit card requests");
            System.out.println("9. Log out");
            int option = Asks.forInt("the option number");

            switch (option) {
                case 1 -> System.out.println(PersonInSession.getInstance().getActualPerson());
                case 2 -> AccountExecutive.showInfo();//Falta hacer los metodos
                case 3 -> Client.register();
                case 4 -> Client.delete();
                case 5 -> Client.modify();
                case 6 -> Client.showInfo();
                case 7 -> Client.showList();
                case 8 -> 
                case 9 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
    }

    public static void CapturistMenu(){
        System.out.println("-----WELCOME TO THE BANK SYSTEM (Capturist)-----");
        while (true) {
                        System.out.println("1. Register account executive");
                        System.out.println("2. Modify account executive information");
                        System.out.println("3. Delete an account executive");
                        System.out.println("4. Show account executives");
                        System.out.println("5. Show personal information of an account executive");
                        System.out.println("6. Log out");
                        int option = Asks.forInt("the option number");

                        switch (option) {
                            case 1 -> AccountExecutive.register();//Falta hacer los metodos
                            case 2 -> AccountExecutive.modify();
                            case 3 -> AccountExecutive.delete();
                            case 4 -> AccountExecutive.showList();
                            case 5 -> AccountExecutive.showInfo();
                            case 6 -> PersonInSession.getInstance().logOut();

                        }
        }
    }

    public static void InvestorMenu(){
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
            System.out.println("6. Consult information about banking transactions");
            System.out.println("7. View credit card requests");
            System.out.println("8. Log out");
            int option = Asks.forInt("the option number");
            switch (option) {
                case 1 -> {
                    switch (askUserType()) {
                        case CLIENT -> Client.register();//Falta hacer los metodos
                        case CAPTURIST -> Capturist.register();
                        case ACCOUNT_EXECUTIVE -> AccountExecutive.register();
                        case INVESTOR -> Investor.register();
                        case MANAGER -> System.out.println("");
                    }
                }

                case 2 -> {
                    switch (askUserType()) {
                        case CLIENT -> Client.delete();
                        case CAPTURIST -> Capturist.delete();
                        case ACCOUNT_EXECUTIVE -> AccountExecutive.delete();
                        case INVESTOR -> Investor.delete();
                        case MANAGER -> System.out.println("Parche");
                    }
                }

                case 3 -> {
                    switch (askUserType()) {
                        case CLIENT -> Client.modify();
                        case CAPTURIST -> Capturist.modify();
                        case ACCOUNT_EXECUTIVE -> AccountExecutive.modify();
                        case INVESTOR -> Investor.modify();
                        case MANAGER -> System.out.println("Parche");
                    }
                }

                case 4 -> {
                    switch (askUserType()) {
                        case CLIENT -> Client.showInfo();
                        case CAPTURIST -> Capturist.showInfo();
                        case ACCOUNT_EXECUTIVE -> AccountExecutive.showInfo();
                        case INVESTOR -> Investor.showInfo();
                        case MANAGER -> System.out.println("Parche");
                    }
                }

                case 5 -> {
                    switch (askUserType()) {
                        case CLIENT -> Client.showList();
                        case CAPTURIST -> Capturist.showList();
                        case ACCOUNT_EXECUTIVE -> AccountExecutive.showList();
                        case INVESTOR -> Investor.showList();
                        case MANAGER -> System.out.println("Parche");
                    }
                }

                case 6 -> 
                case 7 -> 
                case 8 -> {
                    PersonInSession.getInstance().logOut();
                    break;
                }
                default -> System.out.println("An invalid option was entered, please try again");
            }
        }
    }

    
    //Iniciar sesion
    private static void logIn() {
        boolean datosCorrectos = false;
        System.out.println("------------LOG IN TO CONTINUE------------");
        do {
            String username = Asks.forString("user name");
            String password = Asks.forString("password");

            Person person = branchOffices.get(ActualBranchOffice.getInstance().getActualBranchOffice()).verifyLogin(username, password);
            if (person != null) {
                PersonInSession.getInstance().setPerson(person);
                datosCorrectos = true;
                selectMenu();
            } else {
                System.out.println("Username or password wrong, Try again.");
            }
        } while (!datosCorrectos);
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
            int answer = Asks.forInt("the number option");

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
