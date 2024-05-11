package Utils;

import BankSystem.*;
import Users.*;
import java.util.*;
import Users.Utils.*;
import java.time.LocalDate;

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    static Random random = new Random();

    public static ArrayList<String> getCommonData(Role role){
            ArrayList<String> commonData = new ArrayList<>();
            String maternalLastName = "",password ="", paternalLastName="",firstName="",curp="",user="", city="", country= "", address = "";

            String ActualRole = role == Role.CLIENT ? "Client": role == Role.CAPTURIST ? "Capturist" : role == Role.INVESTOR ? "Investor "
                    : role == Role.ACCOUNT_EXECUTIVE ? "Account Executive" : "Manager";
            System.out.println("Role: " + ActualRole);

            do {
                boolean band = false;
                System.out.println("Enter your first name: ");
                try {
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
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine(); // Limpiar el buffer del scanner
                }
            } while (true);
                //

                do {
                    boolean band = false;
                    System.out.println("Enter your father's last name: ");
                    try {
                        paternalLastName = scanner.nextLine();
                
                        // Verificar si el apellido paterno está vacío o contiene números
                        if (paternalLastName.isEmpty()) {
                            band = true;
                        } else {
                            for (int i = 0; i < paternalLastName.length(); i++) {
                                if (Character.isDigit(paternalLastName.charAt(i))) {
                                    band = true;
                                    break;
                                }
                            }
                        }
                
                        // Si el apellido paterno está vacío o contiene números, lanzar una excepción
                        if (band) {
                            throw new IllegalArgumentException("Father's last name with numbers or empty is not valid.");
                        } else {
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);
                
                //

                do {
                    boolean band = false;
                    try {
                        System.out.println("Enter your mother's last name: ");
                        maternalLastName = scanner.nextLine();
                        
                        if (maternalLastName.isEmpty()) {
                            throw new Exception("Mother's last name cannot be empty");
                        } else {
                            for (int i = 0; i < maternalLastName.length(); i++) {
                                if (Character.isDigit(maternalLastName.charAt(i))) {
                                    throw new Exception("Mother's last name cannot contain numbers");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        band = true;
                    }
                    
                    if (band) {
                        System.out.println("Please enter another one.");
                    } else {
                        break;
                    }
                } while (true);
                
            //
            while (true) {
                try {
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
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            //            

            while (true) {
                try {
                    do {
                        System.out.println("Enter the username: ");
                        user = scanner.nextLine();
                        if (user.isEmpty()) {
                            System.out.println("Empty user is not valid");
                        } else {
                            break;
                        }
                    } while (true);
            
                    boolean band2;
                    try {
                        band2 = verifyUser(user);
                    } catch (Exception e) {
                        System.out.println("An error occurred while verifying the user: " + e.getMessage());
                        break;
                    }
            
                    if (!band2) {
                        break;
                    } else {
                        System.out.println("The username is already registered in the system, enter new data");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    break;
                }
            }
            //            

            LocalDate birDate = getBirthDate();
            String rfc = getRFC(firstName, paternalLastName, maternalLastName, birDate);
            String fullLastName = paternalLastName + " " + maternalLastName;
            scanner.nextLine();
            
            do {
                boolean band = false;
                try {
                    System.out.println("Enter your city: ");
                    city = scanner.nextLine();
                    if (city.isEmpty()) {
                        throw new IllegalArgumentException("City cannot be empty");
                    } else {
                        for (int i = 0; i < city.length(); i++) {
                            if (Character.isDigit(city.charAt(i))) {
                                throw new IllegalArgumentException("City cannot contain numbers");
                            }
                        }
                    }
                    break; // Si no hay excepciones, salimos del bucle
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage()); // Mostramos el mensaje de error
                }
            } while (true);
            
                //
                do {
                    boolean band = false;
                    try {
                        System.out.println("Enter your country: ");
                        country = scanner.nextLine();
                        if (country.isEmpty()) {
                            band = true;
                        } else {
                            for (int i = 0; i < country.length(); i++) {
                                if (country.charAt(i) == '1' || country.charAt(i) == '2' || country.charAt(i) == '3' ||
                                        country.charAt(i) == '4' || country.charAt(i) == '5' || country.charAt(i) == '6' ||
                                        country.charAt(i) == '7' || country.charAt(i) == '8' || country.charAt(i) == '9' ||
                                        country.charAt(i) == '0') {
                                    band = true;
                                    break;
                                }
                            }
                        }
                        if (band) {
                            throw new Exception("Country with numbers or empty is invalid.");
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);
                //                

        do {
            System.out.println("Enter your address: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("empty address is invalid, please enter another one");
            } else {
                break;
            }
        } while (true);
        ///
        do {
            try {
                System.out.println("Enter your password: ");
                password = scanner.nextLine();
                
                if (password.isEmpty()) {
                    System.out.println("Empty password is invalid, please enter another one");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (true);
        

        commonData.addAll(Arrays.asList(firstName, fullLastName, birDate.toString(), city, country, rfc, curp, address, password, user));
        return commonData;
    
    }
    
    public static LocalDate getBirthDate(){
        int day, month, year;
        LocalDate birthDate;
        while (true) {
            try {
                System.out.println("Enter the day of birth: ");
                int numero = scanner.nextInt();

                if (numero >= 1 && numero <= 31) {
                    day = numero;
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
                    month = numero;
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
                    year = numero;
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

    public static String getCURP(String firstName, String paternalLastName, String maternalLastName, LocalDate birthDate, String gender, String stateCode) {
        String curp = paternalLastName.substring(0, 2).toUpperCase(Locale.ROOT) +
                      maternalLastName.substring(0, 1).toUpperCase(Locale.ROOT) +
                      firstName.substring(0, 1).toUpperCase(Locale.ROOT) +
                      birthDate.toString().substring(2, 4) +
                      String.format("%02d", birthDate.getMonthValue()) +
                      String.format("%02d", birthDate.getDayOfMonth()) +
                      gender.toUpperCase(Locale.ROOT) +
                      stateCode.toUpperCase(Locale.ROOT);

        curp += getFirstInternalConsonant(paternalLastName) +
                getFirstInternalConsonant(maternalLastName) +
                getFirstInternalConsonant(firstName);

        return curp;
    }

    private static char getFirstInternalConsonant(String word) {
        String consonants = "BCDFGHJKLMNÑPQRSTVWXYZ";
        for (int i = 1; i < word.length(); i++) {
            if (consonants.contains(String.valueOf(word.charAt(i)).toUpperCase(Locale.ROOT))) {
                return word.charAt(i);
            }
        }
        return 0;
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
