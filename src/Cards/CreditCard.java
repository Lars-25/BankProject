package Cards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreditCard extends Card {
    private double credito;
    private final double maxCredito;


    public CreditCard(String firstName, String fullLastName, String type) {
        super(firstName, fullLastName, type);

        
        if (type.equals("Simplicity")) {
            this.maxCredito = 60000;
            this.credito = 60000;
        }
        else if (type.equals("Platinum")) {
            this.maxCredito = 150000;
            this.credito = 150000;
        }
        else {
            this.maxCredito = 400000;
            this.credito = 400000;
        }
    }

    public void charge(double amount) {
        balance += amount;
        LocalDate lastMovementDateTime = LocalDate.now();
        System.out.println("Charged: " + amount);
    }
}