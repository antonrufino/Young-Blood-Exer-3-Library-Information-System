import java.io.Serializable;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
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
    private int a;
  	private static final long serialVersionUID = 1L;

    public User(String name) {										//constructor of a user
    	this.name = name;
    }
    
    public void borrowBook() {
    	System.out.println("\nBorrow Books");
    	
    	Scanner sc = new Scanner(System.in);
    	Scanner sc2 = new Scanner(System.in);
    	Library.viewBooks();
    	
    	System.out.println("\nHow many books you want to borrow?");
    	a = sc.nextInt();
    	
    	System.out.println("Enter title of book/s: ");
    	while (a != 0) {
	    	bookTitle = sc2.nextLine();
	    	a -= 1;
	    	try {
	    		File f = new File("user.txt");
	    		BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
	    		writer.writeLine(get(bookTitle).get(0));
	    		
	    		writer.close();
	    	}
	    	catch (Exception ex) {
	    		System.out.println("Error!");
	    	}
	    	
	    	get(bookTitle).remove(0);
    	}
    }
    
    public void returnBook() {
    	System.out.println("\nReturn Books");
    	
    	Scanner sc = new Scanner(System.in);
    	Scanner sc2 = new Scanner(System.in);
    	
    	System.out.println("\nHow many books you want to return?");
    	a = sc.nextInt();
    	
    	System.out.println("Enter title of book/s: ");
    	while (a != 0) {
	    	bookTitle = sc2.nextLine();
	    	a -= 1;
	    	
	    	try {
	    		File f = new File("user.txt");
	    		BufferedReader reader = new BufferedReader(new FileReader(f));
	    		
	    		remove(bookTitle);
	    		
	    		reader.close();
	    	}
	    	catch (Exception ex) {
	    		System.out.println("Error!");
	    	}
    	}
    	
    }
    
    public void userBooks() {
    	try {
	    	File f = new File("user.txt");
	    	BufferedReader reader = new BufferedReader(new FileReader(f));
	    	String line;
	    	while ((line = reader.readLine()) != null) {
	    		System.out.println(line);
	    	}
	    	reader.close();
	    }
	    catch (Exception ex) {
	    	System.out.println("Error!");
	    }
    }
    
    public void setInfo(String username, String password) {			//function for setting the username and password of the account of user
    	this.username = username;
   	 	this.password = password;
   	}
    
    public String getUsername() {									//getter for username
    	return this.username;
    }
    
    public String getPassword() {									//getter for password
    	return this.password;
    }
	
	public void login(ArrayList<User> users, int size) {			//method that allows user to login
		System.out.println("\nLogin");
		Scanner sc = new Scanner(System.in);
		checker = 0;
		while (checker != 2) {
			System.out.print("Username: ");							//gets the username		
			username = sc.nextLine();	
			System.out.print("Password: ");							//gets the password
			password = sc.nextLine();
			
			this.setInfo(username, password);	
			
			//validate if the username exists and if the password matches the username
			for (int i=0; i<size; i++) {
				if (this.getUsername().equalsIgnoreCase(users.get(i).getUsername()) == true && this.getPassword().equals(users.get(i).getPassword())) {
					checker = 1;
					break;	
				}
			}
			if (checker == 0) System.out.println("Incorrect username and/or password");
			else checker = 2;
		}
	}
	
	public void register(ArrayList<User> users, int size) {			//method that allows user to register/make an account
		System.out.println("\nRegister");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Username: ");	
		username = sc.nextLine();
		
		//checks if username already exists
		while (checker != 2) {
			for (int i=0; i<size; i++) {
				if (username.equalsIgnoreCase(users.get(i).getUsername()) == true) {
					checker = 1;
					break;	
				}
			}
			if (checker == 1) {
				System.out.println("Username already exists");
				System.out.print("Username: ");	
				username = sc.nextLine();
				checker = 0;
			} else checker = 2;
		}
		System.out.print("Password: ");
		password = sc.nextLine();
			
		this.setInfo(username, password);								//sets the username and password of the account of user
		users.add(this);												//adds the user to the list of all users
		System.out.println("Successful");
	}
}
