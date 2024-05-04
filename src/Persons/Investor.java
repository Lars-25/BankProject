package Persons;

public class Investor {
    private String firstName, lastName, city, state;
    private int birthYear;
    private double investmentAmount;

    public Investor(String firstName, String lastName, int birthYear, String city, String state, double investmentAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.birthYear = birthYear;
        this.investmentAmount = investmentAmount;
    }

    public void provideFunds() {
        System.out.println("Funds provided: " + investmentAmount);
    }
}