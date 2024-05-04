package Cards;

import java.time.LocalDate;

abstract class Card {
    protected String cardNumber, cvv;
    protected LocalDate creationDate, expiryDate;
    protected double balance, clabe;
    protected LocalDate lastMovementDateTime;

    public Card(String cardNumber, LocalDate creationDate, double balance, String cvv, double clabe, LocalDate expiryDate) {
        this.cardNumber = cardNumber;
        this.creationDate = creationDate;
        this.balance = balance;
        this.cvv = cvv;
        this.clabe = clabe;
        this.expiryDate = expiryDate;
    }
}