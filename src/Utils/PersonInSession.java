package Utils;

import Persons.User;

public class PersonInSession {

    private static PersonInSession instance;
    private User actualPerson;

    private PersonInSession() {
    }

    public static PersonInSession getInstance() {
        if (instance == null) {
            instance = new PersonInSession();
        }
        return instance;
    }

    public User getActualPerson() {
        return actualPerson;
    }

    public void setPerson(User person) {
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
