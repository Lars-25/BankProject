package BankSystem;

import java.util.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void executeMenu(){
        while (true) {
            System.out.println("\nWelcome to the Bank System");
            System.out.println("Please choose your BranchOffice");
            System.out.println("1. Madero");
            System.out.println("2. Acueducto");
            System.out.println("3. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Thank you for using the system");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    public static void ClientMenu(){

    }

    public static void AccountExecutiveMenu(){
        
    }

    public static void CapturistMenu(){
        
    }

    public static void InvestorMenu(){
        
    }

    public static void ManagerMenu(){
        
    }

}
