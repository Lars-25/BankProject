package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public abstract class Investor extends User {
    private LocalDate startDate;
    private ActualBranchOffice actualBranchOffice;

    public Investor(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, LocalDate startDate, ActualBranchOffice actualBranchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, role);
        this.startDate = startDate;
        this.actualBranchOffice = actualBranchOffice;
    }

    public static void makeInvestment() {

    }

}