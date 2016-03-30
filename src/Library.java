/**
 * Class name: Library
 * Description: This will hold the main file for the program.
 */

import java.util.Scanner;

public class Library {
    public static void main (String[] args) {
        mainMenu();
    }

    private static void printMainMenu() {
        System.out.println("[1] Borrow");
        System.out.println("[2] Return");
        System.out.println("[3] View books in library");
        System.out.println("[4] View borrowed books");
        System.out.println("[0] Exit");

        System.out.print("Choice: ");
    }

    private static void mainMenu() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Borrow coming soon.");
                    break;
                case 2:
                    // TODO: Retun book ui here.
                    System.out.println("Return coming soon.");
                    break;
                case 3:
                    // TODO: View books ui here.
                    System.out.println("View books coming soon.");
                    break;
                case 4:
                    // TODO: View borrowed books ui here.
                    System.out.println("View borrowed books coming soon.");
                    break;
                case 0:
                    System.out.println("Good bye.");
                    break;
                default:
                    System.out.println("Not a valid choice");
            }
        }
    }
}
