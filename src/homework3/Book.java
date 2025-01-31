package homework3;

public class Book {

    private String title, publishYear, status;
    private int id;
    //Has in concept Relationship
    private Author author;

    public void setAuthor(Author author) {
        this.author = author;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", author=" + author +
                '}';
    }
}
