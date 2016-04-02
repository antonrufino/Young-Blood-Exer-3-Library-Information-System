import java.io.Serializable;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class name: User
 * Class description: This handles actions by the user.
 */

public class User implements Serializable {
	private String username;
    private String password;
    private String bookTitle;
    public String name;
    private int checker = 0;
  	private static final long serialVersionUID = 1L;

	private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
	Scanner scan = new Scanner(System.in);

    public User(String name, FileInputStream fis) {										//constructor of a user
    	this.name = name;

		ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(fis);
            borrowedBooks = (ArrayList<Book>)ois.readObject();
            ois.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	public User(String name) {				//constructor of a user
    	this.name = name;
	}

    public void borrowBook(Library lib, String title) {
    	borrowedBooks.add(lib.bookList.get(title).get(0));
    	lib.bookList.get(title).remove(0);
    }

    public void returnBook(Library lib, String id) {
		int indexOfBook;
		String title;
		boolean foundBook = false;

		for(indexOfBook = 0; indexOfBook < borrowedBooks.size(); indexOfBook++) {
			System.out.println(id);
			if(borrowedBooks.get(indexOfBook).getId().equals(id)) {
				foundBook = true;
				break;
			}
		}

		if(foundBook) {
			lib.bookList.get(borrowedBooks.get(indexOfBook).getTitle()).add(borrowedBooks.get(indexOfBook));
			borrowedBooks.remove(indexOfBook);
		}

    }

    public void userBooks() {
    	for(int i = 0; i < borrowedBooks.size(); i++) {
			System.out.println(borrowedBooks.get(i).getTitle());
			System.out.println(borrowedBooks.get(i).getId());
		}
    }

    public void setInfo(String username, String password) {			//function for setting the username and password of the account of user
    	this.username = username;
   	 	this.password = password;
   	}

    public String getUsername() {		//getter for username
    	return this.username;
    }

    public String getPassword() {		//getter for password
    	return this.password;
    }

	public void login(ArrayList<User> users) {			//method that allows user to login
		System.out.println("\nLogin");

		checker = 0;
		while (checker != 2) {
			System.out.print("Username: ");							//gets the username
			username = scan.nextLine();
			System.out.print("Password: ");							//gets the password
			password = scan.nextLine();

			this.setInfo(username, password);

			//validate if the username exists and if the password matches the username
			for (int i = 0; i < users.size(); i++) {
				if (this.getUsername().equalsIgnoreCase(users.get(i).getUsername()) == true &&
					this.getPassword().equals(users.get(i).getPassword())) {
					checker = 1;
					break;
				}
			}
			if (checker == 0) {
				 System.out.println("Incorrect username and/or password");
			} else {
				checker = 2;
			}
		}
	}

	public void register(ArrayList<User> users) {			//method that allows user to register/make an account
		System.out.println("\nRegister");

		System.out.print("Username: ");
		username = scan.nextLine();

		//checks if username already exists
		while (checker != 2) {
			if (checker == 1) {
				System.out.println("Username already exists");
				System.out.print("Username: ");
				username = scan.nextLine();
				checker = 0;
			} else {
				checker = 2;
			}
		}
		System.out.print("Password: ");
		password = scan.nextLine();

		this.setInfo(username, password);	//sets the username and password of the account of user
		users.add(this);					//adds the user to the list of all users
		System.out.println("Successful");
	}

	public void saveToFile() {
		FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream("bin/bbooks.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(borrowedBooks);
            oos.close();
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}
