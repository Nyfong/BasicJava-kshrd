package homework3;
public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[50]; // Array of Book objects
        Logic logic = new Logic(books); // Pass the array to Logic
        logic.displayMenu(); // Start the program
    }
}
