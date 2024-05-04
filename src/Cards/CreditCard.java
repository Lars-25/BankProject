package Cards;

import java.time.LocalDate;

public class CreditCard extends Card {
    public CreditCard(String cardNumber, LocalDate creationDate, double balance, String cvv, double clabe, LocalDate expiryDate) {
        super(cardNumber, creationDate, balance, cvv, clabe, expiryDate);
    }

    public void charge(double amount) {
        balance += amount;
        LocalDate lastMovementDateTime = LocalDate.now();
        System.out.println("Charged: " + amount);
    }
}