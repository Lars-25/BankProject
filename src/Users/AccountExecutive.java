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

import static BankSystem.Bank.users;
import static Utils.Utils.getCommonData;

public class AccountExecutive extends Employee {

    public AccountExecutive(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, BranchOfficeRole branch, String username, double salary, LocalDate startDate) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, Role.ACCOUNT_EXECUTIVE, branch, salary, startDate, username);
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
        BranchOfficeRole branchOffice = UserInSession.getActualUser().getBranchOfficeRole();

        AccountExecutive accountExecutive = new AccountExecutive(firstName, lastName, birthDate, city, country, rfc, curp, address, password, branchOffice, commonData.get(9), getSalary(), getStartDate());

        if (!users.containsKey(branchOffice)) {
            users.put(branchOffice, new HashMap<>());
        }
        if (!users.get(branchOffice).containsKey(Role.ACCOUNT_EXECUTIVE)) {
            users.get(branchOffice).put(Role.ACCOUNT_EXECUTIVE, new ArrayList<>());
        }

        users.get(branchOffice).get(Role.ACCOUNT_EXECUTIVE).add(accountExecutive);
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
                System.out.printf("City: %s %s\n", accountExecutive.getCity());
                System.out.printf("Country: %s %s\n", accountExecutive.getCountry());
                System.out.printf("CURP: %s %s\n", accountExecutive.getCurp());
                System.out.printf("RFC: %s %s\n", accountExecutive.getRfc());
                System.out.printf("Address: %s %s\n", accountExecutive.getAddress());
            }
        }
    }

}
