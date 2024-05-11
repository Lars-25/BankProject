package Users;

import java.time.LocalDate;

import BankSystem.*;
import BankSystem.Utils.BranchOfficeRole;
import Users.Utils.Role;
import Cards.*;
import Utils.UserInSession;
import Utils.*;
import java.util.*;
import java.time.LocalDate;


import static BankSystem.Bank.users;
import static Utils.Utils.getCommonData;


public class Client extends User {
    private final DebitCard debitCard;
    private final ArrayList<CreditCard> creditCards;
    private final ArrayList<RequestCard> cardRequests;

    public Client(String firstName, String lastName, LocalDate birthDate, String city, String country, String rfc, String curp, String address, String password, String username, BranchOfficeRole branchOfficeRole) {
        super(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, Role.CLIENT, branchOfficeRole);
        this.debitCard = new DebitCard(firstName, lastName, "Debit");
        creditCards = new ArrayList<>();
        cardRequests = new ArrayList<>();
    }


    public static void registerClient() {
        System.out.println("You have selected the option to register a client.");
        ArrayList<String> commonData = getCommonData(Role.CLIENT);

        String firstName = commonData.get(0);
        String lastName = commonData.get(1);
        LocalDate birthDate = LocalDate.parse(commonData.get(2));
        String city = commonData.get(3);
        String country = commonData.get(4);
        String rfc = commonData.get(5);
        String curp = commonData.get(6);
        String address = commonData.get(7);
        String password = commonData.get(8);
        String username = commonData.get(9);
        BranchOfficeRole branchOfficeRole = UserInSession.getActualUser().getBranchOfficeRole();

        Client client = new Client(firstName, lastName, birthDate, city, country, rfc, curp, address, password, username, branchOfficeRole);

        if (!users.containsKey(branchOfficeRole)) {
            users.put(branchOfficeRole, new HashMap<>());
        }
        if (!users.get(branchOfficeRole).containsKey(Role.CLIENT)) {
            users.get(branchOfficeRole).put(Role.CLIENT, new ArrayList<>());
        }

        users.get(branchOfficeRole).get(Role.CLIENT).add(client);
        System.out.println(client.getUsername() + " " + client.getPassword());
    }

    public static void showInfoAllClients() {
        try {
            ArrayList<User> users = Bank.users.get(UserInSession.getActualUser().getBranchOfficeRole()).get(Role.CLIENT);
            String branchOffice = UserInSession.getActualUser().getBranchOfficeRole().name();
            if (users.isEmpty()) {
                System.out.println("There are no clients in the branch " + branchOffice);
            } else {
                System.out.println("Clients in the branch " + branchOffice + ":");
                for (User user : users) {
                    try {
                        Client client = (Client) user;
                        System.out.printf("Username: %s\n", client.getUsername());
                        System.out.printf("Name: %s %s\n", client.getFirstName(), client.getLastName());
                        System.out.printf("Birthdate: %s\n", client.getBirthDate());
                        System.out.printf("City: %s\n", client.getCity());
                        System.out.printf("Country: %s\n", client.getCountry());
                        System.out.printf("CURP: %s\n", client.getCurp());
                        System.out.printf("RFC: %s\n", client.getRfc());
                        System.out.printf("Address: %s\n", client.getAddress());
                        client.showCards();
                    } catch (Exception e) {
                        System.out.println("Error retrieving client details: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving clients information: " + e.getMessage());
        }
    }
    

    public static void showClientActualBranch() {
        BranchOfficeRole branchOfficeRole = UserInSession.getActualUser().getBranchOfficeRole();
    }

    public void showCards() {
        System.out.println("Financial Information:\n");
        this.debitCard.showCard();
        for (CreditCard credit : creditCards) {
            System.out.println("");
            credit.showCard();
        }
    }

    public void requestCreditCard() {
        try {
            boolean flagS = true;
            boolean flagP = true;
            boolean flagG = true;
            Scanner scanner = new Scanner(System.in);
            for (CreditCard credit : creditCards) {
                if (credit.getType().equals("Simplicity")) {
                    flagS = false;
                }
                if (credit.getType().equals("Platinum")) {
                    flagP = false;
                }
                if (credit.getType().equals("Gold")) {
                    flagG = false;
                }
            }
            if (!flagS && !flagP && !flagG) {
                System.out.println("You already have all types of cards, no more can be requested");
                return;
            }
            if (debitCard.getBalance() >= 200000 && flagG) {
                System.out.printf("Your balance is $%.2f, you can request the Gold card.\n", debitCard.getBalance());
                System.out.println("Do you want to apply? Y/N: ");
                String option = scanner.nextLine();
                if ("Y".equalsIgnoreCase(option)) {
                    new RequestCard((Client)UserInSession.getActualUser(), "Gold");
                    System.out.println("Card successfully requested.");
                } else {
                    System.out.println("Card not requested.");
                }
            } else if (debitCard.getBalance() >= 100000 && flagP) {
                System.out.printf("Your balance is $%.2f, you can request the Platinum card.\n", debitCard.getBalance());
                System.out.println("Do you want to apply? Y/N: ");
                String option = scanner.nextLine();
                if ("Y".equalsIgnoreCase(option)) {
                    new RequestCard((Client)UserInSession.getActualUser(), "Platinum");
                    System.out.println("Card successfully requested.");
                } else {
                    System.out.println("Card not requested.");
                }
            } else if (debitCard.getBalance() >= 50000 && flagS) {
                System.out.printf("Your balance is $%.2f, you can request the Simplicity card.\n", debitCard.getBalance());
                System.out.println("Do you want to apply? Y/N: ");
                String option = scanner.nextLine();
                if ("Y".equalsIgnoreCase(option)) {
                    new RequestCard((Client)UserInSession.getActualUser(), "Simplicity");
                    System.out.println("Card successfully requested.");
                } else {
                    System.out.println("Card not requested.");
                }
            } else {
                System.out.println("You are currently unable to request any credit cards.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void viewCreditCardRequests() {
        if (!cardRequests.isEmpty()) {
            System.out.println("** Card Requests **");
            for (RequestCard request : cardRequests) {
                System.out.println("Request Number: " + cardRequests.indexOf(request));
                request.showRequest();
            }
        }
    }

    public void viewCreditCardRequestsInProcess() {
        if (!cardRequests.isEmpty()) {
            System.out.println("** Card Requests **");
            for (RequestCard request : cardRequests) {
                if ("In process".equals(request.getStatus())) {
                    System.out.println("Request Number: " + cardRequests.indexOf(request));
                    request.showRequest();
                }
            }
        }
    }

    public boolean checkRequest() {
        boolean flagS = true, flagP = true, flagG = true;
        for (RequestCard request : cardRequests) {
            if ("In process".equals(request.getStatus())) {
                return false;
            }
        }
        for (CreditCard credit : creditCards) {
            if ("Simplicity".equals(credit.getType())) flagS = false;
            if ("Platinum".equals(credit.getType())) flagP = false;
            if ("Gold".equals(credit.getType())) flagG = false;
        }
        return (!flagG && flagP && debitCard.getBalance() >= 100000) || (!flagP && flagS && debitCard.getBalance() >= 50000) || creditCards.isEmpty();
    }

    public void printCreditCardIndex() {
        for (CreditCard card : creditCards) {
            System.out.println("[" + creditCards.indexOf(card) + "]");
            card.showCard();
        }
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public ArrayList<RequestCard> getCardRequests() {
        return cardRequests;
    }
}