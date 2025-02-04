package homework3;
public class Book {
    private int id;
    private String title;
    private String publicationYear;
    private Author author;
    private String status; // "available" or "borrowed"

    public Book(int id, String title, Author author, String publicationYear, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = status;
    }


    public Book() {

    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }

    public Author getAuthor() { return author; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Year: " + publicationYear +
                ", Author: " + author.getName() + ", Status: " + status;
    }

    public void setPublishYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getPublishYear() {
        return publicationYear;
    }
}
