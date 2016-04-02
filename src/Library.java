/*
 * Class name: Library
 * Class description: This will handle library functions.
 */

 import java.util.*;
 import java.io.*;

public class Library implements Serializable{
    /*
        Creates a HashMap that use the Book's Title as the key and a list of books
        with the same title as the value
    */
    private Random rand = new Random();
    public static HashMap<String, ArrayList<Book>> bookList = new HashMap<String, ArrayList<Book>>();
    private static int totalNumOfBooks;

    public Library(FileReader csv) {
        /* Retrieves books from a csv file and randomly generate a booklist */
        Book book;
        ArrayList<Book> books;
        String token[];
        int size;

        String title;
        String id;
        String author;
        String type;
        int yearPublished;

        try {
            BufferedReader br = new BufferedReader(csv);
            while((token = br.readLine().split(",")) != null) {
                size = 2/*rand.nextInt(6) + 15*/;
                books = new ArrayList<Book>(size);

                title = token[0];
                author = token[1];
                yearPublished = Integer.parseInt(token[2]);
                type = token[3];

                for(int i = 0; i < size; i++) {
                    /*
                        Creates instances of book with different id and add it to
                        the list
                    */
                    id = Integer.toHexString(++totalNumOfBooks + 10000000);
                    book = new Book(id, title, author, yearPublished, type);
                    books.add(book);
                }
                bookList.put(title, books); // Adds the list to the hashmap
            }
        } catch(Exception e) {}
    }


    public Library(FileInputStream ser, boolean fileFound) {
        /* Retrieves books from a serialized file */
        ObjectInputStream ois;

        try {
            ois = new ObjectInputStream(ser);
            bookList = (HashMap<String, ArrayList<Book>>)ois.readObject();
            ois.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void findBook(String id) {
        /* Finds book using book's id */
        ArrayList<Book> books;
        Iterator it = bookList.keySet().iterator();
        String title;
        boolean isBookFound = false;

        while (it.hasNext() && !isBookFound) {
            title = (String)it.next();
            books = bookList.get(title);

            for(int i = 0; i < books.size(); i++) {
                if(books.get(i).getId().equals(id)) {
                    System.out.println("Book Found!");
                    /* Prints book's information */
                    System.out.println("\n\t~oOo~\n");
                    printBookInfo(books.get(i));
                    System.out.println("\n\t~oOo~");
                    isBookFound = true;
                    break;
                }
            }
        }
        if(!isBookFound) {
            System.out.println("Book Not Found!");
        }
    }


    public void findBooks(String title) {
        /* Prints all instances of book with the same title */
        ArrayList<Book> books;

        if(bookList.containsKey(title)) {
            books = bookList.get(title);

            for(int i = 0; i < books.size(); i++) {
                /* Prints book's information */
                System.out.println("\n\t~oOo~\n");
                printBookInfo(books.get(i));
            }
            System.out.println("\n\t~oOo~");
        } else {
            System.out.println("Book Not Found!");
        }
    }

    public void viewBooks() {
        /*  Prints all available books */
        ArrayList<Book> books;
        Iterator it = bookList.keySet().iterator();
        String title;

        while (it.hasNext()) {
            title = (String)it.next();
            books = bookList.get(title);

            for(int i = 0; i < books.size(); i++) {
                System.out.println("\n\t~oOo~\n");
                printBookInfo(books.get(i));
            }
            System.out.println("\n\t~oOo~");
        }
    }

    private void printBookInfo(Book book) {
        /* Prints the book's information */
        System.out.println(" Title of the Book:");
        System.out.println("  -" + book.getTitle());

        System.out.println(" Book ID:");
        System.out.println("  -" + book.getId());

        System.out.println(" Author of the Book:");
        System.out.println("  -" + book.getAuthor());

        System.out.println(" Year the Book Published:");
        System.out.println("  -" + book.getYearPublished());
    }

    public void saveToFile() {
      /* Serialize the booklist(hashmap) */
      FileOutputStream fos;
      ObjectOutputStream oos;

      try {
          fos = new FileOutputStream("bin/books.ser");
          oos = new ObjectOutputStream(fos);
          oos.writeObject(bookList);
          oos.close();
          fos.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
    }
}
