package BankSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Users.*;
import Users.Utils.Role;
import BankSystem.Utils.BranchOfficeRole;


public class Bank {

    public float funds;

    public Bank(float funds){
        this.funds = funds;
    }

    public float getFunds(){
        return funds;
    }

    public void setFunds(float funds){
        this.funds = funds;
    }

    public Bank(){}

    public static HashMap<BranchOfficeRole, HashMap<Role, ArrayList<User>> > users = new HashMap<>();

    public static ArrayList<Long> numCards  = new ArrayList<>();

    public User verifyLogIn(String username, String password) {
        for (Map.Entry<BranchOfficeRole, HashMap<Role, ArrayList<User>>> bracnhOfficeEntry : users.entrySet()) {
            HashMap<Role, ArrayList<User>> rolesByBranchOffice = bracnhOfficeEntry.getValue();
            for (ArrayList<User> users : rolesByBranchOffice.values()) {
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return user;
                    }
                }
            }
        }
        return null;
    }


    public static void updateInformation(User user) {
        User.updateInformation(user);
    }

    public static ArrayList<Long> getNumCards() {
        return numCards;
    }

    public void addClient() {
        Client.registerClient();
    }
    
    public void addInvestor() {
        Investor.registerInvestor();
    }
    
    public void addManager() {
        Manager.registerManager();
    }
    
    public void addAccountExecutive() {
        AccountExecutive.registerAccountExecutive();
    }
    
    public void addCapturist() {
        Capturist.registerCapturist();
    }
    
    public void showAllUsers() {
        User.showAllUsers();
    }
    
    public void showInfoAllClients() {
        Client.showInfoAllClients();
    }
    
    public void showinfoAllCapturist() {
        Capturist.showInfoAllCapturists();
    }
    
    public void showAllAccountExecutives() {
        AccountExecutive.showInfoAllAccountExecutives();
    }
    
    public void showInfoAllInvestors() {
        Investor.showInfoAllInvestors();
    }
    
    public void showInfoAllManagers() {
        Manager.showAllManagers();
    }
    


}
