package main.java.com.company.app.entities.util;

import static main.java.com.company.app.entities.util.Constants.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOCUtil {

    public static Scanner scanner = new Scanner(System.in);

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static int getInt(String message) {

        var value = 0;
        while (value == 0) {
            try {
                writeMessage(message);
                value = scanner.nextInt();
            }
            catch(InputMismatchException ex) {
                writeMessage(MENU_INCORRECT_INPUT);
                scanner.next();
            }
        }
        return value;
    }

    public static String getString(String message) {
        writeMessage(message);
        return scanner.next();
    }

}
