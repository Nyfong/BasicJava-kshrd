package homework3;

public class Author {
    private String name;
    private String yearsActive;

    public Author(String name, String yearsActive) {
        this.name = name;
        this.yearsActive = yearsActive;
    }

    public Author() {

    }

    public void setName(String name) {
        this.name = name;
    }
    public void setYearActive(String yearActive) {
        this.yearsActive = yearActive;
    }
    public String getName() {
        return name;
    }

    public String getYearsActive() {
        return yearsActive;
    }

    @Override
    public String toString() {
        return name + " (" + yearsActive + ")";
    }



}
