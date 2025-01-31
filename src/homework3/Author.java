package homework3;

public class Author {
    private   String name;
    private String yearActive;

    public void setName(String name) {
        this.name = name;
    }

    public void setYearActive(String yearActive) {
        this.yearActive = yearActive;
    }

    public String getName() {
        return name;
    }

    public String getYearActive() {
        return yearActive;
    }
    // Display author info
    public void displayAuthorInfo() {
        System.out.println("Author: " + this.name);
        System.out.println("Active Years: " + this.yearActive);
    }
}
