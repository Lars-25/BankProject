package Persons;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.Role;

public abstract class Client extends User {
    private LocalDate registrationDate;
    private BranchOffice branchOffice;

    public Client(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, LocalDate registrationDate, BranchOffice branchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, role);
        this.registrationDate = registrationDate;
        this.branchOffice = branchOffice;
    }

    public void requestCard() {

    }

    public void viewStatus() {

    }

    public void deposit() {

    }

    public void withdraw() {

    }

}