package Users;

import java.time.LocalDate;
import BankSystem.*;
import Users.Utils.Role;

public class AccountExecutive extends Employee {
    public AccountExecutive(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, double salary, LocalDate startDate, ActualBranchOffice actualBranchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, Role.ACCOUNT_EXECUTIVE, salary, startDate, actualBranchOffice);
    }

    public static void registerClient(){
        
    }

    public static void modifyClient(){
        
    }

    public static void deleteClient(){
        
    }

    public static void searchClient(){
        
    }

    public static void showInfoClient(){
        
    }

    public static void authorizeCard(){
        
    }

    public static void denyCard(){
        
    }
    
}