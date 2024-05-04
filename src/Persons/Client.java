package Persons;

import java.time.LocalDate;
import java.util.*;
import BankSystem.*;
import Cards.*;

public class Client {
    private String firstName, lastName, city, state, rfc, curp, address;
    private int birthYear;
    private LocalDate registrationDate;
    private BranchOffice branch;
    private DebitCard debitCard;
    private List<CreditCard> creditCards;

    public Client(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address, LocalDate registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.city = city;
        this.state = state;
        this.rfc = rfc;
        this.curp = curp;
        this.address = address;
        this.registrationDate = registrationDate;
        this.creditCards = new ArrayList<>();
    }

    public static void register() {
        System.out.println("Client registered: " + firstName + " " + lastName);
    }

    public DebitCard assignDebitCard() {
        // this.debitCard = new DebitCard();
        return debitCard;
    }
}