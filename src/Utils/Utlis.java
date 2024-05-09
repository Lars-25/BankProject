package Utils;

import BankSystem.*;
import BankSystem.Utils.*;
import Users.*;
import java.util.*;
import Users.Utils.*;
import java.time.LocalDate;

public class Utlis {
    static Scanner scanner = new Scanner(System.in);

    public static ArrayList<String> getCommonData(Role role){
        ArrayList<String> commonData = new ArrayList<>();
        String maternalLastName = "",password ="", paternalLastName="",firstName="",curp="",user="", city="", country= "", address = "";

        String ActualRole = role == Role.CLIENT ? "Client": role == Role.CAPTURIST ? "Capturist" : role == Role.INVESTOR ? "Investor "
                : role == Role.ACCOUNT_EXECUTIVE ? "Account Executive" : "Manager";
        System.out.println("Role: " + ActualRole);

        do {
            boolean band = false;
            System.out.println("Enter your first name: ");
            firstName = scanner.nextLine();
            if (firstName.isEmpty()) {
                band = true;
            } else{
                for (int i = 0; i < firstName.length(); i++) {
                    if (firstName.charAt(i) == '1' || firstName.charAt(i) == '2' || firstName.charAt(i) == '3' || firstName.charAt(i) == '4' || firstName.charAt(i) == '5' || firstName.charAt(i) == '6' || firstName.charAt(i) == '7' || firstName.charAt(i) == '8' || firstName.charAt(i) == '9' || firstName.charAt(i) == '0') {
                        band = true;
                        break;
                    }
                }
            }
            if (band){
                System.out.println("Name with numbers or is empty is not valid, please enter another one.");
            } else {
                break;
            }
        } while (true);

        do {
            boolean band = false;
            System.out.println("Enter your father's last name: ");
            paternalLastName = scanner.nextLine();
            if (paternalLastName.isEmpty()) {
                band = true;
            } else{
                for (int i = 0; i < paternalLastName.length(); i++) {
                    if (paternalLastName.charAt(i) == '1' || paternalLastName.charAt(i) == '2' || paternalLastName.charAt(i) == '3' || paternalLastName.charAt(i) == '4' || paternalLastName.charAt(i) == '5' || paternalLastName.charAt(i) == '6' || paternalLastName.charAt(i) == '7' || paternalLastName.charAt(i) == '8' || paternalLastName.charAt(i) == '9' || paternalLastName.charAt(i) == '0'){
                        band = true;
                        break;
                    }
                }
            }
            if (band){
                System.out.println("Father's last name with numbers or empty is not valid, please enter another one. ");
            } else {
                break;
            }
        } while (true);

        do {
            boolean band = false;
            System.out.println("Enter your mother's last name: ");
            maternalLastName = scanner.nextLine();
            if (maternalLastName.isEmpty()) {
                band = true;
            } else{
                for (int i = 0; i < maternalLastName.length(); i++) {
                    if (maternalLastName.charAt(i) == '1' || maternalLastName.charAt(i) == '2' || maternalLastName.charAt(i) == '3' || maternalLastName.charAt(i) == '4' || maternalLastName.charAt(i) == '5' || maternalLastName.charAt(i) == '6' || maternalLastName.charAt(i) == '7' || maternalLastName.charAt(i) == '8' || maternalLastName.charAt(i) == '9' || maternalLastName.charAt(i) == '0'){
                        band = true;
                        break;
                    }
                }
            }
            if (band){
                System.out.println("Mother's last name with numbers or empty is not valid, please enter another one. ");
            } else {
                break;
            }
        } while (true);

        while (true) {

            do {
                System.out.println("Enter the CURP: ");
                curp = scanner.nextLine();
                if (curp.isEmpty()) {
                    System.out.println("Empty CURP is not valid");
                } else{
                    break;
                }
            } while (true);


            boolean band1 = verifyCURP(curp);

            if (!band1) {
                break;
            } else {
                System.out.println("The CURP is already registered in the system, enter new data");
            }
        }

        while (true) {
            do {
                System.out.println("Enter the username: ");
                user = scanner.nextLine();
                if (user.isEmpty()) {
                    System.out.println("Empty user is not valid");
                } else{
                    break;
                }
            }  while (true);

            boolean band2 = verifyUser(user);

            if (!band2){
                break;
            } else {
                System.out.println("El nombre de usuario ya esta registrada en el sistema, ingresa datos nuevos.");
            }
        }


        public static LocalDate getBirthDate(){
            int day, month, year;
            LocalDate birthDate;
            while (true) {
                try {
                    System.out.println("Enter the day of birth: ");
                    int numero = scanner.nextInt();
    
                    if (numero >= 1 && numero <= 31) {
                        dia = numero;
                        break;
                    } else {
                        System.out.println("Invalid number. Must be an integer between 01 and 31.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid entry. Please enter a whole number.");
                    scanner.nextLine(); 
                }
            }
            while (true) {
                try {
                    System.out.println("Enter the month of birth:  ");
                    int numero = scanner.nextInt();
    
                    if (numero >= 1 && numero <= 12) {
                        mes = numero;
                        break;
                    } else {
                        System.out.println("Invalid number. Must be an integer between 01 and 12.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid entry. Please enter a whole number.");
                    scanner.nextLine();
                }
            }
            while (true) {
                try {
                    System.out.println("Enter the year of birth: ");
                    int numero = scanner.nextInt();
    
                    if (numero >= 1900 && numero <= 2006) {
                        ano = numero;
                        break;
                    } else {
                        System.out.println("Invalid number. Must be an integer between 1900 y 2006. ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid entry. Please enter a whole number.");
                    scanner.nextLine();
                }
            }
            return birthDate = LocalDate.of(year, month, day);        

        }

        public static String getRFC(String firstName, String paternalLastName, String maternalLastName, LocalDate birthDate){

            String rfcBase = paternalLastName.substring(0, 2).toUpperCase() +
                    maternalLastName.charAt(0) +
                    firstName.charAt(0) +
                    birthDate.toString().substring(2, 4) +
                    birthDate.getMonthValue() +
                    birthDate.getDayOfMonth();
    
    
            String randomCharacters = generateRandomCharacters(3);
    
            return rfcBase + randomCharacters;
        }

        private static String generateRandomCharacters(int length) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder(length);
            Random random = new Random();
    
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                sb.append(characters.charAt(index));
            }
    
            return sb.toString();
        }

        public static boolean verifyCURP(String curp){
            for (HashMap<Role, ArrayList<User>> BranchOfficeRole : Bank.users.values()){
                for(ArrayList<User> users : BranchOfficeRole.values()){
                    for(User user : users){
                        if(user.getCurp().equals(curp)){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static boolean verifyUser(String username) {
            for (HashMap<Role, ArrayList<User>> BranchOfficeRole : Bank.users.values()){
                for(ArrayList<User> users : BranchOfficeRole.values()){
                    for(User user : users){
                        if(user.getUsername().equals(username)){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static long getCardNumber(){
            Random rand = new Random();
            boolean band = true;
            long number;
            while (true){
                number = rand.nextLong(9000000000000000L) + 1000000000000000L;
                for (long card: Bank.getNumCards()){
                    if(card == number){
                        band = false;
                        break;
                    }
                }
                if(band){
                    break;
                }
            }
            Bank.getNumCards().add((long) number);
            return number;
        }



    
}

}
