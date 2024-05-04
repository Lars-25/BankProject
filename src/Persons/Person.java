package Persons;


public abstract class Person {
    protected String firstName, lastName, city, state, rfc, curp, address;
    protected int birthYear;
    
    public Person(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.city = city;
        this.state = state;
        this.rfc = generateRFC();
        this.curp = curp;
        this.address = address;
    }

    private String generateRFC() {
        // Simplified RFC generation logic
        return firstName.substring(0, 2) + lastName.substring(0, 2) + birthYear;
    }
}