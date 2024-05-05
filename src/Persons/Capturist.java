package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public class Capturist extends Employee {
    public Capturist(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, double salary, LocalDate startDate, BranchOffice branchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, Role.CAPTURIST, salary, startDate, branchOffice);
    }

    public void registerAccountExecutive(){
        
    }

    public void modifyAccountExecutive(){
        
    }

    public void deleteAccountExecutive(){
        
    }

    public void searchAccountExecutive(){
        
    }
    
}