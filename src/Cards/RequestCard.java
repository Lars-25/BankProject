package Cards;

import java.time.LocalDate;
import Cards.*;
import Users.Client;
import BankSystem.*;
import BankSystem.Utils.BranchOfficeRole;


public class RequestCard {
    private Client client;
    private String type;
    private String status;
    private LocalDate date;
    private double balance;
    private BranchOfficeRole branchOfficeRole;


    public RequestCard(Client client, String type) {
        this.client = client;
        this.type = type;
        this.balance = client.getDebitCard().getBalance();
        this.status = "In procces";
        this.date = LocalDate.now();
        this.branchOfficeRole = client.getBranchOfficeRole();

        client.getCardRequests().add(this);
    }

    public void approveRequest() {
        client.getCreditCards().add(new CreditCard(client.getFirstName(), client.getLastName(), type));
        this.status = "Approved";
    }

    public void rejectRequest() {
        this.status = "Rejected";
    }

    public void showRequest() {
        System.out.println("Applicant's username: " + client.getUsername());
        System.out.println("Applicant's name: " + client.getFirstName() + " " + client.getLastName());
        System.out.println("Status: " + status);
        System.out.println("Balance on debit: $" + balance);
        System.out.println("Type of card requested: " + type);
        System.out.println("Date of request: " + date);
    }

    public Client getClient() {
        return client;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }

    public BranchOfficeRole getBranchOfficeRole() {
        return branchOfficeRole;
    }


}


    


