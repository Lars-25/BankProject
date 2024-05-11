package Cards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import Users.Client;

public class CreditCard extends Card {
    private double credit;
    private final double maxCredit;


    public CreditCard(String firstName, String fullLastName, String type) {
        super(firstName, fullLastName, type);

        
        if (type.equals("Simplicity")) {
            this.maxCredit = 60000;
            this.credit = 60000;
        }
        else if (type.equals("Platinum")) {
            this.maxCredit = 150000;
            this.credit = 150000;
        }
        else {
            this.maxCredit = 400000;
            this.credit = 400000;
        }
    }

    public double getCredit() {
        return this.credit;
    }

    public void setCredito(double credit) {
        this.credit = credit;
    }

    public void showCard(){
        System.out.println("Type: " + super.type);
        System.out.println("Card Number: " + super.cardNumber);
        System.out.println("Date of creation: " + super.creationDate);
        System.out.println("Date of expiration: " + super.expiryDate);
        System.out.println("Credit: " + credit);
        System.out.println("CLABE: " + super.clabe);
        System.out.println("CVV: " + super.cvv);
        System.out.println("Date of last movement: " + super.dateLastMovement);
        System.out.println();
    }

    public void payWithCard(double amount) {
        if (this.credit >= amount) {
            this.credit -= amount;
            super.dateLastMovement = LocalDateTime.now();
            System.out.println("Credit Spend: " + credit);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void payCard(Client client){
        if (client.getDebitCard().getBalance() - (maxCredit - this.credit) >= 0){
            client.getDebitCard().withdrawMoney(maxCredit - this.credit);
            this.credit = maxCredit;
        }
        else{
            System.out.println("Insufficient balance on debit card");
        }

        super.dateLastMovement = LocalDateTime.now();
    }

    @Override
    public boolean verifyClabe() {
        Scanner scanner = new Scanner(System.in);
        int clabe;
        try {
            System.out.println("Enter the CLABE (Interbank Account Number) of the credit card:");
            clabe = scanner.nextInt();
            scanner.nextLine();
            if (clabe == this.clabe) {
                System.out.println("Correct CLABE");
                return true;
            } else {
                System.out.println("Incorrect CLABE");
                return false;
            }
        } catch (InputMismatchException e) {
            System.out.println("You must enter an integer");
        }
        return false;
    }

}