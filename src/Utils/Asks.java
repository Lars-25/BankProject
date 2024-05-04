package Utils;

import java.util.Scanner;

import BankSystem.Utils.Role;

public class Asks {

      static Scanner sc = new Scanner(System.in);

    //Para respuestas
    public static String forString(String cad) {
        print(cad);
        String answer = sc.next();
        sc.nextLine();
        return answer;
    }

    public static double forDouble(String cad) {
        print(cad);
        double answer = sc.nextDouble();
        return answer;
    }

    public static int forInt(String cad) {
        print(cad);
        int answer = sc.nextInt();
        return answer;
    }

    public static long forLong(String cad) {
        print(cad);
        long answer = sc.nextInt();
        return answer;
    }

    public static boolean forBoolean(String cad) {
        System.out.printf("IS %s?", cad);
        System.out.println("1. Yes");
        System.out.println("2. No");
        int answer = sc.nextInt();
        if (answer == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static void print(String cad) {
        System.out.printf("\nEnter %s: ", cad);
    }

    

}
