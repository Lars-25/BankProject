package Users;

import java.time.LocalDate;
import BankSystem.*;
import BankSystem.Utils.*;
import Users.Utils.Role;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Employee extends User {
    protected double salary;
    protected LocalDate startDate;

    public Employee(String firstName, String lastName, LocalDate birthDate, String city, String state, String rfc, String curp, String address, String password, Role role, BranchOfficeRole branchOfficeRole, double salary, LocalDate startDate, String username) {
        super(firstName, lastName, birthDate, city, state, rfc, curp, address, password, role, branchOfficeRole, username);
        this.salary = salary;
        this.startDate = startDate;
    }

    public static LocalDate getStartDate() {
        Scanner scanner = new Scanner(System.in);
        int day, month, year;
        LocalDate startDate;
        while (true) {
            try {
                System.out.println("Enter the start date of employment (day): ");
                int number = scanner.nextInt();

                if (number >= 1 && number <= 31) {
                    day = number;
                    break;
                } else {
                    System.out.println("Invalid number. It must be an integer between 01 and 31.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Enter the start date of employment (month): ");
                int number = scanner.nextInt();

                if (number >= 1 && number <= 12) {
                    month = number;
                    break;
                } else {
                    System.out.println("Invalid number. It must be an integer between 01 and 12.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Enter the start date of employment (year): ");
                int number = scanner.nextInt();

                if (number >= 1920 && number <= LocalDate.now().getYear()) {
                    year = number;
                    break;
                } else {
                    System.out.println("Invalid number. It must be an integer between 1920 and the current year.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
        return startDate = LocalDate.of(year, month, day);
    }

    public static double getSalary() {
        Scanner scanner = new Scanner(System.in);
        double salary = 0.0;
        boolean validInput = false;
        
        do {
            try {
                System.out.println("Enter the desired salary for this employee: ");
                salary = scanner.nextDouble();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid salary value.");
                scanner.nextLine(); 
            }
        } while (!validInput);
    
        return salary;
    }

}
