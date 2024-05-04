package BankSystem;

import java.time.LocalDate;
import java.util.Scanner;

public class Bank {

    private static final Scanner scanner = new Scanner(System.in);

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
    
}
