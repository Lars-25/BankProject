package Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import BankSystem.*;
import BankSystem.Utils.BranchOfficeRole;
import Users.*;
import Users.Utils.Role;


public class Capturist extends Employee {
    public Capturist(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, double salary, LocalDate startDate, ActualBranchOffice actualBranchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, Role.CAPTURIST, salary, startDate, actualBranchOffice);
    }

    public static void registerAccountExecutive(){
        
    }

    public static void modifyAccountExecutive(){
        
    }

    public static void deleteAccountExecutive(){
        
    }

    public static void searchAccountExecutive(){
        
    }
    
}