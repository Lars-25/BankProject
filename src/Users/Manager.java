package Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BankSystem.*;
import BankSystem.Utils.BranchOfficeRole;
import Utils.*;
import Users.Utils.Role;
import Users.*;
import Utils.UserInSession;
import static Utils.Utils.getCommonData;
import static BankSystem.Bank.users;


public class Manager extends Employee {

    public Manager(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, String username, BranchOfficeRole branchOfficeRole, double salary, LocalDate startDate) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, Role.MANAGER, branchOfficeRole, salary, startDate);
    }

    public static void registerManager() {
        System.out.println("You have selected the option to register a branch manager.");
        ArrayList<String> commonData = getCommonData(Role.MANAGER);

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
        double salary = getSalary();
        LocalDate startDate = getStartDate();

        Manager manager = new Manager(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, branchOfficeRole, salary, startDate);

        if (!users.containsKey(branchOfficeRole)) {
            users.put(branchOfficeRole, new HashMap<>());
        }
        if (!users.get(branchOfficeRole).containsKey(Role.MANAGER)) {
            users.get(branchOfficeRole).put(Role.MANAGER, new ArrayList<>());
        }

        users.get(branchOfficeRole).get(Role.MANAGER).add(manager);
    }

    public static void showInfoAllManagers() {
            HashMap<BranchOfficeRole, HashMap<Role, ArrayList<User>>> usersByBranchOffice = users;
            for (Map.Entry<BranchOfficeRole, HashMap<Role, ArrayList<User>>> branchOfficeEntry : usersByBranchOffice.entrySet()) {
                BranchOfficeRole branchOfficeRole = branchOfficeEntry.getKey();
                HashMap<Role, ArrayList<User>> rolesAtBranchOffice = branchOfficeEntry.getValue();
    
                if (rolesAtBranchOffice.containsKey(Role.MANAGER)) {
                    ArrayList<User> branchOfficeManagers = rolesAtBranchOffice.get(Role.MANAGER);
                    System.out.println("Branch Office managers at the branch " + branchOfficeRole + ":");
                    for (User manager : branchOfficeManagers) {
                            System.out.printf("Name: %s %s\n", manager.getFirstName(), manager.getLastName());
                            System.out.printf("Birthdate: %s\n", manager.getBirthDate().toString());
                            System.out.printf("City: %s\n", manager.getCity());
                            System.out.printf("Country: %s\n", manager.getCountry());
                            System.out.printf("CURP: %s\n", manager.getCurp());
                            System.out.printf("RFC: %s\n", manager.getRfc());
                            System.out.printf("Address: %s\n", manager.getAddress());
                            System.out.printf("Username: %s\n", manager.getUsername());
                            System.out.printf("Password: %s\n", manager.getPassword());
                    }
                } else {
                    System.out.println("There are no branch managers at the branch " + branchOfficeRole);
                }
            }
    }

    @Override
    public void showPersonalInfo() {
        super.showPersonalInfo();
    }
}
