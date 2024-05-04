package BankSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import BankSystem.Utils.Role;
import Persons.Person;

public class Bank {

    private static final Scanner scanner = new Scanner(System.in);
    public HashMap<Role, ArrayList<Person>> personLists = new HashMap<>();

    public LocalDate askForDate(){
        int month, day, year;
        while(true){
            System.out.print("Month: ");
            month = scanner.nextInt();
            System.out.print("Day: ");
            day = scanner.nextInt();
            System.out.print("Year: ");
            year = scanner.nextInt();
            scanner.nextLine();
            if(month < 1 || month > 12 || day < 1 || day > 31){
                System.out.println("The specified date is not valid!");
                System.out.println("Please enter a valid date...");
            }else{
                break;
            }
        }
        
        LocalDate date = LocalDate.of(year, month, day);

        return date;
    }


    public Person verifyLogin(String username, String password) {
    if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
        return manager;
    }
    for (ArrayList<Person> list : personLists.values()) {
        for (Person person : list) {
            if (username.equals(person.getUsername())) {
                if (password.equals(person.getPassword())) {
                    return person;
                }
            }
        }
    }
    return null;
}

    
}
