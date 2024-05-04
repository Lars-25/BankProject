package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public abstract class Employee extends Person {
    protected double salary;
    protected Role role;
    protected LocalDate startDate;
    protected BranchOffice branch;
    protected String password;

    public Employee(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address, double salary, Role role, LocalDate startDate, String password) {
        super(firstName, lastName, birthYear, city, state, rfc, curp, address);
        this.salary = salary;
        this.role = role;
        this.startDate = startDate;
        this.password = password;
    }

    public abstract void performDuties();
}