package Users;

import java.time.LocalDate;
import java.util.Scanner;

import BankSystem.Utils.BranchOfficeRole;
import Users.Utils.Role;
import Utils.Utils.*;
import Utils.*;

public class User {
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String city;
    protected String country;
    protected String rfc;
    protected String curp;
    protected String address;
    protected String password;
    protected String username;
    protected Role role;
    protected BranchOfficeRole branchOfficeRole;

    public User(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc,
            String curp, String address, String password, Role role, BranchOfficeRole branchOfficeRole,
            String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
        this.country = country;
        this.rfc = rfc;
        this.curp = curp;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
        this.branchOfficeRole = branchOfficeRole;
    }

    public static void showAllUsers(){
        try {
            System.out.println("******** Clients ********");
        Client.showInfoAllClients();
        System.out.println("\n******** Investors ********");
        Investor.showInfoAllInvestors();
        System.out.println("\n******** Ejecutivos de cuenta ********");
        AccountExecutive.showInfoAllAccountExecutives();
        System.out.println("\n******** Capturistas ********");
        Capturist.showInfoAllCapturists();
        } catch (Exception e) {
            System.out.println("There is no users");
        }
        
    }



    public static void updateInformation(User user){
        Scanner scanner = new Scanner(System.in);
        String option = "";
        Role role = user.getRole();
        do {
            System.out.println("What you want to change for the selected user. ");
            System.out.println("1. City of residence");
            System.out.println("2. Country of residence");
            System.out.println("3. Address");
            System.out.println("4. Password");
            System.out.println("5. Username");
            System.out.println("6. Exit");
            option = scanner.nextLine();
            
            if (option.equals("1")) {
                System.out.println("Enter the new city of residence: ");
                String city = scanner.nextLine();
                user.setCity(city);
                System.out.println("City successfully changed");
            } else if (option.equals("2")) {
                System.out.println("Enter the new country of residence: ");
                String country = scanner.nextLine();
                user.setCountry(country);
                System.out.println("Country successfully changed");
            } else if (option.equals("3")) {
                System.out.println("Enter the new address: ");
                String address = scanner.nextLine();
                user.setAddress(address);
                System.out.println("Address successfully changed");
            } else if (option.equals("4")) {
                System.out.println("Enter the new password: ");
                String password = scanner.nextLine();
                user.setPassword(password);
                System.out.println("Password successfully changed");
            } else if (option.equals("5")) {
                while (true){
                    System.out.println("Enter the new username: ");
                    String username = scanner.nextLine();

                    boolean band2 = Utils.verifyUser(username);
                    if (!band2){
                        break;
                    } else {
                        System.out.println("The user name is already registered in the system, please enter new data");
                    }
                }
                System.out.println("Username successfully changed");
            } else if (option.equals("6")) {
                System.out.println("Exiting");
                break;
            } else  {
                System.out.println("Invalid Option");
            }

        } while (true);
    }





    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public BranchOfficeRole getBranchOfficeRole(){
        return branchOfficeRole;
    }
    
}