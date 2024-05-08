package BankSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import BankSystem.Utils.Role;
import Persons.User;

public class Bank {
    private static Bank instance;
    private HashMap<Role, ArrayList<User>> usersList = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public Bank() {
        initializeRoles();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    private void initializeRoles() {
        for (Role role : Role.values()) {
            usersList.put(role, new ArrayList<>());
        }
    }

    private void logIn() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = findUser(username, password);
        if (user != null) {
            System.out.println("Login successful. Welcome back, " + user.getFirstName() + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }


    private User findUser(String username, String password) {
        for (ArrayList<User> userList : usersList.values()) {
            for (User user : userList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    public User verifyLogin(String username, String password) {
        return findUser(username, password);
    }

    private void quit() {
        System.out.println("Thank you for using the Bank System. Goodbye!");
        System.exit(0);
    }
}
