package Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BankSystem.*;
import Users.Utils.Role;
import Users.*;
import BankSystem.Utils.*;
import Utils.UserInSession;
import java.util.*;

import static BankSystem.Bank.users;
import static Utils.Utils.getCommonData;


public class AccountExecutive extends Employee {


    public AccountExecutive(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, String username, BranchOfficeRole branchOfficeRole, double salary, LocalDate startDate) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, Role.ACCOUNT_EXECUTIVE, branchOfficeRole, salary, startDate);
    }

    public static void registerAccountExecutive() {
        System.out.println("You have selected the option to register an account executive.");
        ArrayList<String> commonData = getCommonData(Role.ACCOUNT_EXECUTIVE);

        String firstName = commonData.get(0);
        String lastName = commonData.get(1);
        LocalDate birthDate = LocalDate.parse(commonData.get(2));
        String city = commonData.get(3);
        String country = commonData.get(4);
        String rfc = commonData.get(5);
        String curp = commonData.get(6);
        String address = commonData.get(7);
        String password = commonData.get(8);
        String username = commonData.get(9);
        BranchOfficeRole branchOffice = UserInSession.getActualUser().getBranchOfficeRole();

        AccountExecutive accountExecutive = new AccountExecutive(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, branchOffice, getSalary(), getStartDate());

        if (!users.containsKey(branchOffice)) {
            users.put(branchOffice, new HashMap<>());
        }
        if (!users.get(branchOffice).containsKey(Role.ACCOUNT_EXECUTIVE)) {
            users.get(branchOffice).put(Role.ACCOUNT_EXECUTIVE, new ArrayList<>());
        }

        users.get(branchOffice).get(Role.ACCOUNT_EXECUTIVE).add(accountExecutive);
    }

    public static void updateAccountExecutiveInformation(ArrayList<User> users, Scanner scanner) {
        if (users == null || users.isEmpty()) {
            System.out.println("No account executives registered to update.");
            return;
        }

        boolean found = false;
        while (!found) {
            System.out.println("Available account executives:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }

            System.out.println("Enter the username (or type 'exit' to return):");
            String name = scanner.nextLine();

            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting account executive update process.");
                break;
            }

            for (User user : users) {
                if (user.getUsername().equals(name)) {
                    updateInformation(user);
                    System.out.println("Account executive information successfully updated.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("User entered not found. Try again or type 'exit' to return.");
            }
        }
    }

    public static void deleteAccountExecutive(ArrayList<User> users, Scanner scanner) {
        if (users == null || users.isEmpty()) {
            System.out.println("No account executives registered to delete.");
            return;
        }

        boolean found = false;
        while (!found) {
            System.out.println("Available account executives:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }

            System.out.println("Enter the username (or type 'exit' to return):");
            String name = scanner.nextLine();

            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting account executive removal process.");
                break;
            }

            for (User user : users) {
                if (user.getUsername().equals(name)) {
                    users.remove(user);
                    System.out.println("Account executive correctly removed.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("User entered not found. Try again or type 'exit' to return.");
            }
        }
    }



    public static void showInfoAllAccountExecutives() {
            ArrayList<User> users = Bank.users.get(UserInSession.getInstance().getActualUser().getBranchOfficeRole()).get(Role.ACCOUNT_EXECUTIVE);
            String branchOffice = UserInSession.getInstance().getActualUser().getBranchOfficeRole().name();
            if (users.isEmpty()) {
                System.out.println("There are no account executives at the branch " + branchOffice);
            } else {
                System.out.println("Account executives at the branch " + branchOffice + ":");
                for (User user : users) {
                        AccountExecutive accountExecutive = (AccountExecutive) user;
                        System.out.printf("Name: %s %s\n", accountExecutive.getFirstName(), accountExecutive.getLastName());
                        System.out.printf("Birthdate: %s\n", accountExecutive.getBirthDate().toString());
                        System.out.printf("City: %s\n", accountExecutive.getCity());
                        System.out.printf("Country: %s\n", accountExecutive.getCountry());
                        System.out.printf("CURP: %s\n", accountExecutive.getCurp());
                        System.out.printf("RFC: %s\n", accountExecutive.getRfc());
                        System.out.printf("Address: %s\n", accountExecutive.getAddress());
                }
            }
    }

}
