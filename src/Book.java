/**
 * Class name: Book
 * Class description: This will represent a book in the library.
 */

public class Book {
    private int id;
    private String title;
    private String author;
    private int yearPublished;

    public Book(int id, String title, String author, int yearPublished) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public void getId() { return this.id; }
    public void getTitle() { return this.title; }
    public void getAuthor() { return this.author; }
    public void getYearPublished() { return this.yearPublished; }
}
