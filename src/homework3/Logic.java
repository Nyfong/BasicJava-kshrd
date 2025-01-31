package homework3;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;

public class Logic extends Book {
    Scanner sc = new Scanner(System.in);
    public String userName;
    public String libraryAddress;
    public int option;
    public String input;
    public String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
    public boolean isValid = false; // Controls the loop
    public String[][] books = {
            {"The Art of War", "Sun Tzu", "500 BC", "Available"},
            {"KDET PUMIND", "George Orwell", "1949", "NOT Available"},
            {"To Kill a Mockingbird", "Harper Lee", "1960", "Available"},
            {"Moby-Dick", "Herman Melville", "1851", "Available"},
            {"The Catcher in the Rye", "J.D. Salinger", "1951", "Available"}
    };
    String[] headers = {"ID", "Book", "Author", "When","Available"};

    //method for setup Library
    public void setUpLibrary() {
        System.out.println("=================== SET UP LIBRARY ===================");
        // Validate Library's Name
        while (!isValid) {
            System.out.print("Enter Library's Name: ");
            userName = sc.nextLine();
            if (!userName.matches("[a-zA-Z ]+")) { // Only allows letters
                System.out.println(RED + "Invalid username. Only letters are allowed." + RESET);
            } else {
                isValid = true; // Exit loop
            }
        }
        isValid = false; // Reset for the next input
        // Validate Library's Address
        while (!isValid) {
            System.out.print("Enter Library's Address: ");
            libraryAddress = sc.nextLine();
            if (!libraryAddress.matches("[a-zA-Z ]+")) { // Only allows letters
                System.out.println(RED + "Invalid address. Only letters are allowed." + RESET);
            } else {
                isValid = true; // Exit loop
            }
        }
        System.out.println("Hello  \" " + GREEN + this.userName.toUpperCase() + RESET + " \" Library is already created in [ " + GREEN + this.libraryAddress.toUpperCase() + RESET + " ] address successfully");
    }

    public void addBook() {
        int bookId;
        String bookName, authorName;
        Author auth = new Author();
        Scanner sc = new Scanner(System.in);  // Make sure to create a Scanner object
        System.out.println("========= ADD BOOK INFO =========");
        // Asking for Book ID
        boolean isValid = false;
        while (!isValid) {
            System.out.print("=> Book ID : ");
            String input = sc.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println(RED + "Invalid bookID. Only digits are allowed." + RESET);
            } else {
                bookId = Integer.parseInt(input);
                setId(bookId);  // Set book ID
                System.out.println("The book ID is " + this.getId());
                isValid = true;
            }
        }
        // Reset isValid for next loop
        isValid = false;

        // Asking for Book Name
        while (!isValid) {
            System.out.print("=> Book Name : ");
            String input = sc.nextLine();
            if (!input.matches("[a-zA-Z ]+")) {
                System.out.println(RED + "Invalid bookName. Only letters are allowed." + RESET);
            } else {
                bookName = input;
                setTitle(bookName);  // Set book title
                System.out.println("The book name is " + this.getTitle());
                isValid = true;
            }
        }
        // Reset isValid for next loop
        isValid = false;

        // Asking for Book Author
        while (!isValid) {
            System.out.print("=> Book Author : ");
            String input = sc.nextLine();
            if (!input.matches("[a-zA-Z ]+")) {
                System.out.println(RED + "Invalid bookAuthor. Only letters are allowed." + RESET);
            } else {
                authorName = input;
                auth.setName(authorName);  // Set author's name
                setAuthor(auth);  // Assign Author object to Book
                System.out.println("The book author is " + this.getAuthor().getName());
                isValid = true;
            }
        }
        // Asking for Author's Year Active
        isValid = false;
        while (!isValid) {
            System.out.print("=> Author Year Active: ");
            String input = sc.nextLine();
            if (!input.matches("[0-9]{4}-[0-9]{4}")) { // Check for valid year range, e.g., "1953-1999"
                System.out.println(RED + "Invalid year format. Please enter a valid year range." + RESET);
            } else {
                auth.setYearActive(input);  // Set the author's active years
                System.out.println("The author was active from " + auth.getYearActive());
                isValid = true;
            }
        }
        // Asking for Published Year
        isValid = false;
        while (!isValid) {
            System.out.print("=> Published Year : ");
            String input = sc.nextLine();
            if (!input.matches("\\d{4}")) {  // Validate published year
                System.out.println(RED + "Invalid year. Please enter a valid year." + RESET);
            } else {
                setPublishYear(input);  // Set book's published year
                System.out.println("The book was published in " + this.getPublishYear());
                isValid = true;
            }
        }

        // Displaying Success Message
        System.out.println("Book is added successfully ✅");
        System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
    }

    public void displayMenu() {
        boolean condition = true;
        do{
            System.out.println("========= " + this.userName + " LIBRARY, " + this.libraryAddress + " =========");
            System.out.println("1- Add Book");
            System.out.println("2- Show All Books");
            System.out.println("3- Show Available Books");
            System.out.println("4- Borrow Book");
            System.out.println("5- Return Book");
            System.out.println("6- Exit");
            System.out.println("-----------------------------------------");
            System.out.print("=> Choose option(1-6): ");
            input = sc.nextLine();
            if (input.matches("\\d+")) {
                option = Integer.parseInt(input);
                switch (option) {
                    case 1 -> {
                        this.addBook();
                        sc.nextLine();
                    }
                    case 2 -> {
                        Table table = new Table(headers.length, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL, false);
                        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                        //display header Books
                        for (String header : headers) {
                            table.addCell(header, cellStyle);
                        }

                       for(int i =0; i< books.length ; i+=1)
                       {
                           for(int j =0; j<books[i].length; j+=1)
                           {
                               table.addCell(""+(i+=1), cellStyle); // Type
                               table.addCell(books[i][0], cellStyle); // Type
                               table.addCell(books[i][1], cellStyle); // Type
                               table.addCell(books[i][2], cellStyle); // Type
                               table.addCell(books[i][3], cellStyle); // Type

                           }
                       }
                        System.out.println(table.render());
                        System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
                        sc.nextLine();
                    }

                    default -> System.out.println("End 200");
                }
            }else System.out.println(RED + "Invalid Only [ 1-6 ] are allowed." + RESET);
        }while (condition);
    }

}
