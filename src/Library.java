/**
 * Class name: Library
 * Description: This will hold the main file for the program.
 */

import java.util.Scanner;

public class Library {
    private static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        mainMenu();
    }

    private static void printMainMenu() {
        System.out.println("[1] Borrow");
        System.out.println("[2] Return");
        System.out.println("[0] Exit");

        System.out.print("Choice: ");
    }

    private static void mainMenu() {
        int choice = -1;

        while (choice != 0) {
            printMainMenu();

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input is not a number.");
                System.exit(1);
            }

            switch (choice) {
                case 1:
                    // TODO: Borrow book ui here.
                    borrowMenu();
                    break;
                case 2:
                    // TODO: Retun book ui here.
                    returnMenu();
                    break;
                case 0:
                    System.out.println("Good bye.");
                    break;
                default:
                    System.out.println("Not a valid choice");
            }
        }
    }

    private static void borrowMenu() {
        String title;

        System.out.print("Book title: ");
        scanner.nextLine();
        title = scanner.nextLine();

        // TODO: Borrow implementation goes here.

        System.out.println("You borrowed " + title + ".");
    }

    private static void returnMenu() {
        String title;

        System.out.print("Book title: ");
        scanner.nextLine();
        title = scanner.nextLine();

        // TODO: Borrow implementation goes here.

        System.out.println("You returned " + title + ". Thank you.");
    }
}
