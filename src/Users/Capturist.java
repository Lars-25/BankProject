package Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import BankSystem.*;
import BankSystem.Utils.BranchOfficeRole;
import Users.*;
import Users.Utils.Role;
import Utils.UserInSession;

import static BankSystem.Bank.users;
import static Utils.Utils.getCommonData;

public class Capturist extends Employee {


    public Capturist(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, String username, BranchOfficeRole branchOfficeRole, double salary, LocalDate startDate) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, Role.CAPTURIST, branchOfficeRole, salary, startDate);
    }

    public static void registerCapturist() {
        System.out.println("You have selected the option to register a capturist");
        ArrayList<String> commonData = getCommonData(Role.CAPTURIST);

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
        BranchOfficeRole branchOfficeRole = UserInSession.getActualUser().getBranchOfficeRole();

        Capturist capturist = new Capturist(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, branchOfficeRole, getSalary(), getStartDate());

        if (!users.containsKey(branchOfficeRole)) {
            users.put(branchOfficeRole, new HashMap<>());
        }
        if (!users.get(branchOfficeRole).containsKey(Role.CAPTURIST)) {
            users.get(branchOfficeRole).put(Role.CAPTURIST, new ArrayList<>());
        }

        users.get(branchOfficeRole).get(Role.CAPTURIST).add(capturist);
    }

    public static void updateCapturistInformation(ArrayList<User> users, Scanner scanner) {
        if (users == null || users.isEmpty()) {
            System.out.println("No capturists registered to update.");
            return;
        }

        boolean found = false;
        User finalUser = null;
        while (!found) {
            System.out.println("Available capturists:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }
            System.out.println("Enter the username (or type 'exit' to return):");
            String name = scanner.nextLine();

            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting capturist update process.");
                break;
            }

            for (User user : users) {
                if (user.getUsername().equals(name)) {
                    finalUser = user;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("User entered not found. Try again or type 'exit' to return.");
            }
        }

        if (finalUser != null) {
            try {
                Capturist.updateInformation(finalUser);
                System.out.println("Capturist information successfully updated.");
            } catch (Exception e) {
                System.out.println("Error updating capturist: " + e.getMessage());
            }
        }
    }

    public static void deleteCapturist(ArrayList<User> users, Scanner scanner) {
        if (users == null || users.isEmpty()) {
            System.out.println("No capturists registered to delete.");
            return;
        }

        boolean found = false;
        while (!found) {
            System.out.println("Available capturists:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }
            System.out.println("Enter the username (or type 'exit' to return):");
            String name = scanner.nextLine();

            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting capturist removal process.");
                break;
            }

            for (User user : users) {
                if (user.getUsername().equals(name)) {
                    users.remove(user);
                    System.out.println("Capturist correctly removed.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("User entered not found. Try again or type 'exit' to return.");
            }
        }
    }



    public static void showInfoAllCapturists() {
            ArrayList<User> users = Bank.users.get(UserInSession.getInstance().getActualUser().getBranchOfficeRole()).get(Role.CAPTURIST);
            String branchOfficeRole = UserInSession.getInstance().getActualUser().getBranchOfficeRole().name();
            if (users.isEmpty()) {
                System.out.println("There are no capturists in the branch " + branchOfficeRole);
            } else {
                System.out.println("Capturists in the branch " + branchOfficeRole + ":");
                for (User user : users) {
                        Capturist capturist = (Capturist) user;
                        System.out.printf("Name: %s %s\n", capturist.getFirstName(), capturist.getLastName());
                        System.out.printf("Birthdate: %s\n", capturist.getBirthDate().toString());
                        System.out.printf("City: %s\n", capturist.getCity());
                        System.out.printf("Country: %s\n", capturist.getCountry());
                        System.out.printf("CURP: %s\n", capturist.getCurp());
                        System.out.printf("RFC: %s\n", capturist.getRfc());
                        System.out.printf("Address: %s\n", capturist.getAddress());
                }
            }
    }

    @Override
    public void showPersonalInfo() {
        super.showPersonalInfo();
    }
    
}
