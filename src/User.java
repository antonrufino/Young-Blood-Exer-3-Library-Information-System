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
    private int checker = 0;
  	private static final long serialVersionUID = 1L;

	private ArrayList<Book> borrowedBooks = new ArrayList<Book>();

	public User(String username, String password) {				//constructor of a user
    	this.username = username;
		this.password = password;
	}

    public void borrowBook(Library lib, String title) {

		if(lib.bookList.containsKey(title)) {
			borrowedBooks.add(lib.bookList.get(title).get(0));
	    	lib.bookList.get(title).remove(0);
		} else {
			System.out.println("Book not Found");
		}

    }

    public void returnBook(Library lib, String id) {
		int indexOfBook;
		String title = "";
		boolean foundBook = false;

		for(indexOfBook = 0; indexOfBook < borrowedBooks.size(); indexOfBook++) {
			System.out.println(id);
			if(borrowedBooks.get(indexOfBook).getId().equals(id)) {
				title = borrowedBooks.get(indexOfBook).getTitle();
				foundBook = true;
				break;
			}
		}

		if(foundBook) {
			lib.bookList.get(title).add(borrowedBooks.get(indexOfBook));
			borrowedBooks.remove(indexOfBook);
		} else {
			System.out.println("Book not Found");
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

}
