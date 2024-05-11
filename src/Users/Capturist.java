package Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import BankSystem.*;
import BankSystem.Utils.BranchOfficeRole;
import Users.*;
import Users.Utils.Role;
import Utils.UserInSession;

import static BankSystem.Bank.users;
import static Utils.Utils.getCommonData;

public class Capturist extends Employee {

    public Capturist(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, BranchOfficeRole branchOfficeRole, String username, double salary, LocalDate startDate) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, Role.CAPTURIST, branchOfficeRole, salary, startDate, username);
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
        BranchOfficeRole branchOfficeRole = UserInSession.getActualUser().getBranchOfficeRole();

        Capturist capturist = new Capturist(firstName, lastName, birthDate, city, country, rfc, curp, address, password, branchOfficeRole, commonData.get(9), getSalary(), getStartDate());

        if (!users.containsKey(branchOfficeRole)) {
            users.put(branchOfficeRole, new HashMap<>());
        }
        if (!users.get(branchOfficeRole).containsKey(Role.CAPTURIST)) {
            users.get(branchOfficeRole).put(Role.CAPTURIST, new ArrayList<>());
        }

        users.get(branchOfficeRole).get(Role.CAPTURIST).add(capturist);
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
                System.out.printf("Name: %s %s", capturist.getFirstName(), capturist.getLastName());
                System.out.printf("Birthdate: %s", capturist.getBirthDate().toString());
                System.out.printf("City: %s", capturist.getCity());
                System.out.printf("Country: %s", capturist.getCountry());
                System.out.printf("CURP: %s", capturist.getCurp());
                System.out.printf("RFC: %s", capturist.getRfc());
                System.out.printf("Address: %s", capturist.getAddress());
            }
        }
    }
}
