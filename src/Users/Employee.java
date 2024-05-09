package Users;

import java.time.LocalDate;
import BankSystem.*;
import Users.Utils.Role;

public abstract class Employee extends User {
    private double salary;
    private LocalDate startDate;
    private ActualBranchOffice actualBranchOffice;

    public Employee(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, double salary, LocalDate startDate, ActualBranchOffice actualBranchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, role);
        this.salary = salary;
        this.startDate = startDate;
        this.actualBranchOffice = actualBranchOffice;
    }

}