package BankSystem;

import java.util.*;
import Persons.*;
import java.text.SimpleDateFormat;

public class BranchOffice {
    private String name;
    private Map<String, Employee> employees;
    private Map<String, Client> clients;

    public BranchOffice(String name) {
        this.name = name;
        this.employees = new HashMap<>();
        this.clients = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.rfc, employee);
        employee.branch = this;
    }

    public void registerClient(Client client) {
        if (employees.get(client.rfc) instanceof AccountExecutive) {
            clients.put(client.rfc, client);
            System.out.println("Client registered: " + client.firstName);
        } else {
            System.out.println("Only Account Executives can register clients.");
        }
    }
}