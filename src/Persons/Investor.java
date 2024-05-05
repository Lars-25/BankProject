package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public abstract class Investor extends User {
    private LocalDate startDate;
    private BranchOffice branchOffice;

    public Investor(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, LocalDate startDate, BranchOffice branchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, role);
        this.startDate = startDate;
        this.branchOffice = branchOffice;
    }

    public static void makeInvestment() {

    }

}