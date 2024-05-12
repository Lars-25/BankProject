package Users;

import java.time.LocalDate;
import BankSystem.*;
import Users.Utils.Role;
import BankSystem.Utils.*;
import Utils.UserInSession;
import java.time.LocalDate;
import java.util.*;

import static BankSystem.Bank.users;
import static BankSystem.Menu.managerPassword;
import static Utils.Utils.getCommonData;

public class Investor extends User {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<FundRecord> fundRecordsList = new ArrayList<>();

    public Investor(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, String username, BranchOfficeRole branchOfficeRole) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, Role.INVESTOR, branchOfficeRole);
    }


    public static void registerInvestor() {
        System.out.println("You have selected the option to register an investor");
        ArrayList<String> commonData = getCommonData(Role.INVESTOR);

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

        Investor investor = new Investor(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, branchOffice);
        if (!users.containsKey(branchOffice)) {
            users.put(branchOffice, new HashMap<>());
        }
        if (!users.get(branchOffice).containsKey(Role.INVESTOR)) {
            users.get(branchOffice).put(Role.INVESTOR, new ArrayList<>());
        }

        users.get(branchOffice).get(Role.INVESTOR).add(investor);
    }

    public static void updateInvestorInformation(ArrayList<User> users, Scanner scanner) {
        if (users == null || users.isEmpty()) {
            System.out.println("No investors registered");
            return;
        }
        boolean managerAuthorized = managerPassword();
        if (!managerAuthorized) {
            System.out.println("Incorrect manager password.");
            return;
        }

        boolean found = false;
        User finalUser = null;
        while (!found) {
            System.out.println("Available investors:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }
            System.out.println("Enter the username (or type 'exit' to return):");
            String name = scanner.nextLine();

            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting investor update process.");
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
                Investor.updateInformation(finalUser);
                System.out.println("Investor information successfully updated.");
            } catch (Exception e) {
                System.out.println("Error updating investor: " + e.getMessage());
            }
        }
    }

    public static void deleteInvestor(ArrayList<User> users, Scanner scanner) {
        if (users == null || users.isEmpty()) {
            System.out.println("No investors registered to delete.");
            return;
        }

        boolean managerAuthorized = managerPassword();
        if (!managerAuthorized) {
            System.out.println("Incorrect manager password.");
            return;
        }

        boolean found = false;
        while (!found) {
            System.out.println("Available investors:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }
            System.out.println("Enter the username (or type 'exit' to return):");
            String name = scanner.nextLine();

            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting investor removal process.");
                break;
            }

            for (User user : users) {
                if (user.getUsername().equals(name)) {
                    users.remove(user);
                    System.out.println("Investor correctly removed.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("User entered not found. Try again or type 'exit' to return.");
            }
        }
    }



    public static void showInfoAllInvestors() {
            ArrayList<User> users = Bank.users.get(UserInSession.getInstance().getActualUser().getBranchOfficeRole()).get(Role.INVESTOR);
            String branchOffice = UserInSession.getInstance().getActualUser().getBranchOfficeRole().name();
            if (users.isEmpty()) {
                System.out.println("There are no investors in the Branch Office " + branchOffice);
            } else {
                System.out.println("Investors at the Branch Office " + branchOffice + ":");
                for (User user : users) {
                        Investor investor = (Investor) user;
                        System.out.printf("Name: %s %s\n", investor.getFirstName(), investor.getLastName());
                        System.out.printf("Birthdate: %s\n", investor.getBirthDate().toString());
                        System.out.printf("City: %s\n", investor.getCity());
                        System.out.printf("Country: %s\n", investor.getCountry());
                        System.out.printf("CURP: %s\n", investor.getCurp());
                        System.out.printf("RFC: %s\n", investor.getRfc());
                        System.out.printf("Address: %s\n", investor.getAddress());
                }
            }
    }
    

    public static void makeInvestment() {
        try {
            String user = UserInSession.getActualUser().getFirstName();
            System.out.println("Enter the funds you wish to provide to the bank:");
            float funds = sc.nextFloat();
            if (funds <= 0) {
                System.out.println("The amount to invest must be positive");
                return;
            } else {
                Bank bank = new Bank();
                float currentFunds = bank.getFunds();
                float newFunds = currentFunds + funds;
                bank.setFunds(newFunds);
                System.out.println("Added " + funds + " to the Branch Office " + UserInSession.getInstance().getActualUser().getBranchOfficeRole());
            }
            String fundsStr = String.valueOf(funds);
            LocalDate today = LocalDate.now();
            FundRecord newRecord = new FundRecord(user, fundsStr, today);
            fundRecordsList.add(newRecord);
        } catch (Exception e) {
            System.out.println("An error occurred while making the investment: " + e.getMessage());
        }
    }
    

    public static class FundRecord {
        private String name;
        private String funds;
        private LocalDate date;

        public FundRecord(String name, String funds, LocalDate date) {
            this.name = name;
            this.funds = funds;
            this.date = date;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getFunds() {
            return funds;
        }

        public String getName() {
            return name;
        }
    }

    public static void showAllRecords() {
        if (fundRecordsList.isEmpty()) {
            System.out.println("There are no records to show");
            return;
        }

        System.out.println("Funds records:");
        for (FundRecord record : fundRecordsList) {
            System.out.println("Investor Name: " + record.getName() + ", Funds: " + record.getFunds() + ", Date: " + record.getDate());
        }
    }
    
    public static void modifyData() {
        try {
            System.out.println("***Available Investors***");
            showInfoAllInvestors();
            System.out.println("Enter the username of the investor you wish to update:");
            String username = sc.nextLine();
            Investor foundInvestor = null;
    
            for (Map.Entry<BranchOfficeRole, HashMap<Role, ArrayList<User>>> branchEntry : users.entrySet()) {
                HashMap<Role, ArrayList<User>> rolesAtBranch = branchEntry.getValue();
                if (rolesAtBranch.containsKey(Role.INVESTOR)) {
                    ArrayList<User> investors = rolesAtBranch.get(Role.INVESTOR);
                    for (User user : investors) {
                        try {
                            if (user instanceof Investor && user.getUsername().equals(username))
                                foundInvestor = (Investor) user;
                            break;
                        } catch (Exception e) {
                            System.out.println("Error while searching for investor: " + e.getMessage());
                        }
                    }
                }
                if (foundInvestor != null) {
                    break;
                }
            }
            if (foundInvestor != null) {
                updateInformation(foundInvestor);
            } else {
                System.out.println("Investor not found.");
            }
        } catch (Exception e) {
            System.out.println("Error modifying investor data: " + e.getMessage());
        }
    }
    

    public static void deleteInvestor() {
        try {
            System.out.println("Enter the username of the investor you wish to delete:");
            String usernameToDelete = sc.nextLine();
            boolean found = false;
    
            for (Map.Entry<BranchOfficeRole, HashMap<Role, ArrayList<User>>> branchEntry : users.entrySet()) {
                HashMap<Role, ArrayList<User>> rolesAtBranch = branchEntry.getValue();
    
                if (rolesAtBranch.containsKey(Role.INVESTOR)) {
                    ArrayList<User> investors = rolesAtBranch.get(Role.INVESTOR);
    
                    Iterator<User> iterator = investors.iterator();
    
                    while (iterator.hasNext()) {
                        User user = iterator.next();
                        try {
                            if (user instanceof Investor && user.getUsername().equals(usernameToDelete)) {
                                iterator.remove();
                                found = true;
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Error occurred while deleting investor: " + e.getMessage());
                        }
                    }
    
                    if (found) {
                        break; // User found
                    }
                }
            }
    
            if (found) {
                System.out.println("Investor with username " + usernameToDelete + " has been deleted.");
            } else {
                System.out.println("No investor found with username " + usernameToDelete + ".");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

}
