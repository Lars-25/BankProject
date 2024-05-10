package Users;

import java.time.LocalDate;
import BankSystem.*;
import Users.Utils.Role;

public abstract class Client extends User {
    private LocalDate registrationDate;
    private ActualBranchOffice actualBranchOffice;

    public Client(String firstName, String lastName, LocalDate birthYear, String city, String country, String rfc, String curp, String address, String username, String password, Role role, LocalDate registrationDate, ActualBranchOffice actualBranchOffice) {
        super(firstName, lastName, birthYear, city, country, rfc, curp, address, username, password, Role.CLIENT);
        this.registrationDate = registrationDate;
        this.actualBranchOffice = actualBranchOffice;
    }

    public static void requestCard() {

    }

    public static void viewStatus() {

    }

    public static void deposit() {

    }

    public static void withdraw() {

    }

    public static void consultCardInfo() {

    }

    public static void modifyPersonalInfo() {

    }

    

}