package Utils;

import Persons.Person;

public class PersonInSession {

    private static PersonInSession instance;
    private Person actualPerson;

    private PersonInSession() {
    }

    public static PersonInSession getInstance() {
        if (instance == null) {
            instance = new PersonInSession();
        }
        return instance;
    }

    public Person getActualPerson() {
        return actualPerson;
    }

    public void setPerson(Person person) {
        this.actualPerson = person;
    }

    public boolean thereIsActualPerson() {
        return actualPerson != null;
    }

    public void logOut() {
        instance = null;
        actualPerson = null;
    }
}
