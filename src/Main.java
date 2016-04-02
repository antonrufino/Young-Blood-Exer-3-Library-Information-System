import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Library lib;
    private static User user;

	public static void main(String[] args) {
        loadLibrary();
        logInMenu();

        System.out.println(user);
        mainMenu();
        lib.saveToFile();
        lib.saveUsers();
	}

    private static void printMainMenu() {
        System.out.println("[1] Borrow");
        System.out.println("[2] Return");
        System.out.println("[3] View books in library");
        System.out.println("[4] View borrowed books");
        System.out.println("[0] Exit");

        System.out.print("Choice: ");
    }

    private static void logInMenu() {
        String username;
        String password;
        int choice = -1;

        do {
    		System.out.print("[1] Login\n[2] Register\n[0] Exit\nEnter choice: ");
    		choice = scan.nextInt();

            if(choice == 1) {
                //allows user to login
                System.out.print("Username: ");	//gets the username
    			username = scan.next();

    			System.out.print("Password: "); //gets the password
    			password = scan.next();

                user = lib.login(username, password);
    		} else if(choice == 2) {
                //allows user to register
                System.out.print("Username: ");
                username = scan.next();

                System.out.print("Password: ");
                password = scan.next();

                user = lib.register(username, password);
            } else if (choice != 0) {
                System.out.println("Not a valid choice.");
            }
        } while (user == null && choice != 0);

        if (choice == 0) {
            System.out.println("Good bye.");
            System.exit(0);
        }
    }

    private static void mainMenu() {
        int choice = -1;

        while (choice != 0) {
            printMainMenu();

            try {
                choice = scan.nextInt();
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
                case 3:
                    // TODO: View books ui here.
                    lib.viewBooks();
                    break;
                case 4:
                    // TODO: View borrowed books ui here.
                    user.userBooks();
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
        scan.nextLine();
        title = scan.nextLine();
        System.out.println(user);

        if (user.borrowBook(lib, title))
            System.out.println("You borrowed " + title + ".");
    }

    private static void returnMenu() {
        String id;
        user.userBooks();

        System.out.print("Book ID: ");
        scan.nextLine();
        id = scan.nextLine();

        user.returnBook(lib, id);

        System.out.println("You returned " + id + ". Thank you.");
    }

    private static void loadLibrary() {
        File f = new File("bin/books.ser");

        try {
            if(f.exists()) {
                // Retrieves books from existing file
                lib = new Library(new FileInputStream("bin/books.ser"));

            } else {
                // Generates library from a csv file
                lib = new Library(new FileReader("bin/books.csv"));
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
