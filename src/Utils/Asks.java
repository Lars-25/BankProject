package Utils;

import java.util.Scanner;

public class Asks {

    static Scanner sc = new Scanner(System.in);

    public static String forString(String message) {
        System.out.printf("\nEnter %s: ", message);
        String answer = sc.next();
        sc.nextLine();
        return answer;
    }

    public static double forDouble(String message) {
        System.out.printf("\nEnter %s: ", message);
        double answer = sc.nextDouble();
        return answer;
    }

    public static int forInt(String message) {
        System.out.printf("\nEnter %s: ", message);
        int answer = sc.nextInt();
        return answer;
    }

    public static long forLong(String message) {
        System.out.printf("\nEnter %s: ", message);
        long answer = sc.nextInt();
        return answer;
    }

    @Deprecated
    public static boolean forBoolean(String message) {
        System.out.print(message);
        System.out.println("1. Yes");
        System.out.println("2. No");
        int answer = sc.nextInt();
        if (answer == 1) {
            return true;
        } else {
            return false;
        }
    }


}
