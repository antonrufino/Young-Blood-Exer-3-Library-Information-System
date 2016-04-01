import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SuppressWarnings("unchecked")

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library lib;

	public static void main(String[] args) {
		ArrayList <User> users = new ArrayList<User>();

		User user = new User("");													//creates instance of the user
		//Deserialization
		if (new File("users.ser").exists() == true) {								//checks if file exists
			//reading/getting the array list of users from file (if it exists)
			try{
				FileInputStream fileIn = new FileInputStream("users.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				users = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Cannot read.");
			}
		}

		System.out.print("[1] Login\n[2] Register\n[3] Exit\nEnter choice: ");		//prints menu
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		int size = users.size();													//gets the size of the array list of users

		if (choice == 1) user.login(users, size);									//allows user to login
		else if (choice == 2) user.register(users, size);							//allows user to register


		//Serialization
		try {
			//writing/saving information of the users in the arraylist
            FileOutputStream fileOut = new FileOutputStream("users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        loadLibrary();
        mainMenu();
        lib.saveToFile();
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
                case 3:
                    // TODO: View books ui here.
                    lib.viewBooks();
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

    private static void loadLibrary() {
        File f = new File("bin/books.ser");

        try {
            if(f.exists()) {
                // Retrieves books from existing file
                lib = new Library(new FileInputStream("bin/books.ser"), true);
            } else {
                // Generates library from a csv file
                lib = new Library(new FileReader("bin/books.csv"));
            }
        } catch(Exception e) {
            System.out.println("OH SHIT");
            System.exit(1);
        }
    }
}
