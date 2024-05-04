package Cards;

import java.time.LocalDate;

public class DebitCard extends Card {
    public DebitCard(String cardNumber, LocalDate creationDate, double balance, String cvv, double clabe, LocalDate expiryDate) {
        super(cardNumber, creationDate, balance, cvv, clabe, expiryDate);
    }

    public void deposit(double amount) {
        balance += amount;
        LocalDate lastMovementDateTime = LocalDate.now();
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            LocalDate lastMovementDateTime = LocalDate.now();
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}