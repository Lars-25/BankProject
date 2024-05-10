package Cards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DebitCard extends Card {
    private double balance;


    public DebitCard(String firstName, String fullLastName, String type) {
        super(firstName, fullLastName, type);
        this.balance = 0;
    }

    public void mostrarTarjeta(){
        System.out.println("Type: "+ super.type);
        System.out.println("Card Number: "+ super.cardNumber);
        System.out.println("Date of creation: "+ super.creationDate);
        System.out.println("Date of expiration: "+ super.expiryDate);
        System.out.println("Balance: " + balance);
        System.out.println("CLABE: "+ super.clabe);
        System.out.println("CVV: "+ super.cvv);
        System.out.println("Date of last movement: "+ super.dateLastMovement);
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            super.dateLastMovement = LocalDateTime.now();
            System.out.println("Withdrawn " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        this.balance += amount;
        super.dateLastMovement = LocalDateTime.now();
        System.out.println("Deposited: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean verifyClabe() {
        Scanner scanner = new Scanner(System.in);
        int clabe;
        try{
            System.out.println("Enter the CLABE (Interbank Account Number) of the debit card: ");
            clabe = scanner.nextInt();
            scanner.nextLine();
            if(clabe == this.clabe){
                System.out.println("Correct CLABE");
                return true;
            }
            else{
                System.out.println("Incorrect CLABE");
                return false;
            }
        }catch (InputMismatchException e){
            System.out.println("You must enter an integer");
        }
        return false;

    }

    
}