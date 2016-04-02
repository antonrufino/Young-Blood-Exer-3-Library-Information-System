/**
 * Class name: Book
 * Class description: This will represent a book in the library.
 */

import java.io.*;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private int yearPublished;
    private String type;

    public Book(String id, String title, String author, int yearPublished, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.type = type;
    }

    // Getters
    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public int getYearPublished() {
        return this.yearPublished;
    }
}
