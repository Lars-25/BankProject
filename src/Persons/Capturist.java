package Persons;

import java.time.LocalDate;
import BankSystem.*;

public class Capturist extends Employee {
    public Capturist(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address, double salary, LocalDate startDate, String password) {
        super(firstName, lastName, birthYear, city, state, rfc, curp, address, salary, Role.CAPTURIST, startDate, password);
    }

    @Override
    public void performDuties() {
        System.out.println("Modifying account executive data.");
    }

    public void modifyAccountExecutive() {
        System.out.println("Account Executive modified.");
    }
}