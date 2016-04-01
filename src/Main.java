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
	}
}
