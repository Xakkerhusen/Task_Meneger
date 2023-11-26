package org.example;

import java.util.Scanner;

public class ScannerUtil {
    Scanner scanner = new Scanner(System.in);

    public String nextLine(String title) {
        System.out.println(title);
        String result = scanner.nextLine();
        return result;
    }

    public int nextInt(String integer) {
        int result;
        do {
            try {
                System.out.println(integer);
                result = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a number");
                scanner.nextLine();
            }
        } while (true);
        return result;
    }




}
