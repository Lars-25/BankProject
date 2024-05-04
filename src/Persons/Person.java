package Persons;

import BankSystem.Utils.Role;

public abstract class Person {
    protected String firstName, lastName, city, state, rfc, curp, address,username,password;
    protected int birthYear;
    private Role role;
    
    public Person(String firstName, String lastName, int birthYear, String city, String state, String rfc, String curp, String address, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.city = city;
        this.state = state;
        this.rfc = generateRFC();
        this.curp = curp;
        this.address = address;
        this.username=username;
        this.password=password;
        this.role=role;
    }

    private String generateRFC() {
        // Simplified RFC generation logic
        return firstName.substring(0, 2) + lastName.substring(0, 2) + birthYear;
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    
    
}