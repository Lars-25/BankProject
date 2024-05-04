package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public class Manager extends Employee implements Authenticatable {
    public Manager(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address, double salary, LocalDate startDate, String password) {
        super(firstName, lastName, birthYear, city, state, rfc, curp, address, salary, Role.MANAGER, startDate, password);
    }

    @Override
    public void performDuties() {
        System.out.println("Managing the entire branch.");
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void manageSystem() {
        System.out.println("Managing system.");
    }

    public void registerInvestor() {
        System.out.println("Investor registered.");
    }

    public void viewInvestorFunds() {
        System.out.println("Viewing investor funds.");
    }
}