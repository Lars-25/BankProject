package Cards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import Utils.Utils;

public abstract class Card {

    Random rand = new Random();
    protected final String fullName;
    protected final long cardNumber;
    protected final LocalDate creationDate;
    protected final int cvv;
    protected final int clabe;
    protected final LocalDate expiryDate;
    protected LocalDateTime dateLastMovement;
    protected final String type;

    public Card(String firstName, String lastName, String type) {
        this.fullName = firstName + lastName;
        this.cardNumber = Utils.getCardNumber();
        this.creationDate = LocalDate.now();
        this.cvv = rand.nextInt(900)+100;
        this.clabe = rand.nextInt(9000)+1000;
        this.expiryDate = this.creationDate.plusYears(5);
        this.dateLastMovement = LocalDateTime.now();
        this.type = type;
    }

    public abstract boolean verifyClabe();

    public String getFullName() {
        return fullName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public int getClabe() {
        return clabe;
    }

    public LocalDate getExpirDate() {
        return expiryDate;
    }

    public LocalDateTime getDateLastMovement() {
        return dateLastMovement;
    }

    public String getType() {
        return type;
    }


    
}