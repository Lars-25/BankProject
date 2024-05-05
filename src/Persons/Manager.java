package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public class Manager extends Employee {
    public Manager(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, double salary, LocalDate startDate, BranchOffice branchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, Role.MANAGER, salary, startDate, branchOffice);
    }

    public void registerClient(){
        
    }

    public void modifyClient(){
        
    }

    public void deleteClient(){
        
    }

    public void searchClient(){
        
    }

    public void registerInvestor(){
        
    }

    public void deleteInvestor(){
        
    }

    public void searchInvestor(){
        
    }

    public void registerAccountExecutive(){
        
    }

    public void modifyAccountExecutive(){
        
    }

    public void deleteAccountExecutive(){
        
    }

    public void searchAccountExecutive(){
        
    }

    public void authorizeCard(){
        
    }

    public void denyCard(){
        
    }

    public void viewCapturistMovement(){
        
    }

    public void viewInvestorFunds() {
        
    }
}