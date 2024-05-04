package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public class AccountExecutive extends Employee {
    public AccountExecutive(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address, double salary, LocalDate startDate, String password) {
        super(firstName, lastName, birthYear, city, state, rfc, curp, address, salary, Role.ACCOUNT_EXECUTIVE, startDate, password);
    }

    @Override
    public void performDuties() {
        System.out.println("Handling client accounts.");
        System.out.println();
    }
}
