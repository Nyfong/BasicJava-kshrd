package homework3;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Arrays;
import java.util.Scanner;

public class Logic extends Book {
    Scanner sc = new Scanner(System.in);
    public String userName;
    public String libraryAddress;
    public int option;
    public String input;
    public String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
    public boolean isValid = false; // Controls the loop
    public String[][] books = new String[7][];  // Initialize with 50 rows

    {  // Initialization block
        books[0] = new String[]{"The Art of War", "Sun Tzu", " 1200- 1300", "500 BC", getStatus()};
        books[1] = new String[]{"KDET PUMIND", "George Orwell", "1200- 1300", "1949", getStatus()};
        books[2] = new String[]{"To Kill a Mockingbird", "Harper Lee", "1200- 1300", "1960", "Aviable"};
        books[3] = new String[]{"Moby-Dick", "Herman Melville", "1200- 1300", "1851", getStatus()};
        books[4] = new String[]{"The Catcher in the Rye", "J.D. Salinger", "1200- 1300", "1951", getStatus()};
    }

    String[] headers = {"ID", "Book", "Author", "When", "Available"};

    //case 2
    private void printBooks() {
        Table table = new Table(headers.length, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL, false);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        for (String header : headers) {
            table.addCell(header, cellStyle);
        }
        int i = 0;
        int j = 0;
        int idCount = 0;
        for (i = 0; i < books.length; i += 1) {
            if (books[i] != null) {
                table.addCell("" + (idCount + 1), cellStyle); // Type
                table.addCell(books[j][0], cellStyle); // Type
                table.addCell(books[j][1] + "(" + books[j][2] + ")", cellStyle); // Type
                table.addCell(books[j][3], cellStyle); // Type
                table.addCell((books[j][4] == null ? (GREEN + "Available" + RESET) : (RED + "Unavailable" + RESET)), cellStyle); // Type
            }
            j += 1;
            idCount += 1;

        }
        System.out.println(table.render());
    }

    //method for setup Library
    private void setUpLibrary() {
        System.out.println(RESET + "=================== SET UP LIBRARY ===================" + RESET);
        // Validate Library's Name
        while (!isValid) {
            System.out.print("Enter Library's Name: ");
            userName = sc.nextLine();
            if (!userName.matches("[a-zA-Z ]+")) { // Only allows letters
                System.out.println("Invalid username. Only letters are allowed.");
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

    //for helping insert auto Id
    private int countNotNullStorage() {
        int bookId = 0;
        for (int i = 0; i < books.length; i += 1) {
            if (books[i] != null) {
                bookId += 1;
            }
        }
        return bookId;
    }

    private void addBook() {
        int bookId = 0;
        String bookName, authorName;
        Author auth = new Author();
        Scanner sc = new Scanner(System.in);  // Make sure to create a Scanner object
        System.out.println("========= ADD BOOK INFO =========");
        bookId = countNotNullStorage();
        this.setId(bookId);
        if (getId() == books.length) {
            System.out.println(RED + "Full of Storage" + RESET);
            System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
            return;
        }
        System.out.println("Book Id:" + (getId() + 1));
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
        String active = "";
        while (!isValid) {
            System.out.print("=> Author Year Active: ");
            String input = sc.nextLine();
            if (!input.matches("[0-9]{4}-[0-9]{4}")) { // Check for valid year range, e.g., "1953-1999"
                System.out.println(RED + "Invalid year format. Please enter a valid year range." + RESET);
            } else {
                auth.setYearActive(input);  // Set the author's active years
                System.out.println("The author was active from " + auth.getYearActive());
                active = input;
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
        setStatus(null);
        System.out.println(this.getId());
        if (this.getId() < books.length) { // Ensure index is within bounds
            books[this.getId()] = new String[]{
                    this.getTitle(),
                    this.getAuthor().getName(),
                    active,
                    this.getPublishYear(),
                    getStatus()
            };
        } else {
            System.out.println("Error: Book ID is out of bounds!");
        }

        // Displaying Success Message
        System.out.println("Book is added successfully ✅");
        System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
    }

    //show available books case 3
    private void showavailableBook() {
        // Reset color
        Table table = new Table(headers.length, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL, false);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        for (String header : headers) {
            table.addCell(header, cellStyle);
        }
        int i = 0;
        int j = 0;
        int idCount = 0;
        String av = GREEN + "Available" + RESET;
        for (i = 0; i < books.length; i += 1) {
            if (books[i] != null && books[j][4] == null) {
                table.addCell("" + (idCount + 1), cellStyle); // Type
                table.addCell(books[j][0], cellStyle); // Type
                table.addCell(books[j][1] + "(" + books[j][2] + ")", cellStyle); // Type
                table.addCell(books[j][3], cellStyle); // Type
                table.addCell((books[j][4] == null ? av : ""), cellStyle); // Type
            }
            j += 1;
            idCount += 1;
        }
        System.out.println(table.render());
    }

    //borrowBook case 4
    private void borrowBooks() {

        int borrowBookId;
        do {
            System.out.print("-> Enter book Id to borow:");
            input = sc.nextLine();
            if (input.matches("^[1-9]\\d*$")) {
                borrowBookId = Integer.parseInt(input);
                if (borrowBookId > books.length) {
                    System.out.println(RED + "Input the valid id please " + RESET);
                } else if (books[borrowBookId - 1][4] != null) {
                    System.out.println(RED + "Already book" + RESET);
                    System.out.println(RED + "BookiD [" + borrowBookId + "] is Unaviable" + RESET);
                    System.out.println("");
                    System.out.print("Do you want to book again [Y / N ]? :");
                    input = sc.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        isValid = false;
                    } else if (input.equalsIgnoreCase("n")) {
                        System.out.println(GREEN + "Thank you :)" + RESET);
                        isValid = true;
                        return;
                    }
                } else {
                    int i = 0;
                    int j = 0;
                    int idCount = 0;
                    books[borrowBookId - 1][4] = "Aviable";
                    System.out.println("Book Id: " + (borrowBookId ));
                    System.out.println("Book Title: " + (books[borrowBookId - 1][0]));
                    System.out.println("Book Author: " + (books[borrowBookId - 1][1] + " (" + books[borrowBookId - 1][2] + ")"));
                    System.out.println("Book Publishyear: " + (books[borrowBookId - 1][3]));

                    System.out.println(GREEN + ">>>>>>>>>>>> Booking succus <<<<<<<<<<<<" + RESET);
                    System.out.println("");
                    isValid = true;


                }
            } else System.out.println(RED + "Input only degit and positive " + RESET);
        } while (!isValid);

    }

    //returningBook
    private void returnBooking() {
        int returnBookId;
        do {
            System.out.print("-> Enter book Id to return:");
            input = sc.nextLine();
            if (input.matches("^[1-9]\\d*$")) {
                returnBookId = Integer.parseInt(input);
                if (returnBookId > books.length) {
                    System.out.println(RED + "Input the valid id please " + RESET);
                } else if (books[returnBookId - 1][4] == null) {
                    System.out.println(RED + "Can not return book" + RESET);
                    System.out.println("BookiD [" + returnBookId + "] is" + GREEN + " Aaviable" + RESET);
                    System.out.println("");
                    System.out.print("Do you want to book again [Y / N ]? :");
                    input = sc.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        isValid = false;
                    } else if (input.equalsIgnoreCase("n")) {
                        System.out.println(GREEN + "Thank you :)" + RESET);
                        isValid = true;
                        return;
                    }
                } else {
                    int i = 0;
                    int j = 0;
                    int idCount = 0;
                    books[returnBookId - 1][4] = null;
                    System.out.println("");
                    System.out.println("Book Id: " + (returnBookId ));
                    System.out.println("Book Title: " + (books[returnBookId - 1][0]));
                    System.out.println("Book Author: " + (books[returnBookId - 1][1] + " (" + books[returnBookId - 1][2] + ")"));
                    System.out.println("Book Publishyear: " + (books[returnBookId - 1][3]));
                    System.out.println("");
                    System.out.println(GREEN + ">>>>>>>>>>>> Book is return  succusfully <<<<<<<<<<<<" + RESET);
                    System.out.println("");
                    isValid = true;


                }
            } else System.out.println(RED + "Input only degit and positive " + RESET);
        } while (!isValid);
    }

    //main case
    public void displayMenu() {
        boolean condition = true;
        setUpLibrary();
        do {
            System.out.println("========= " + userName.toUpperCase() + " LIBRARY, " + libraryAddress.toUpperCase() + " =========");
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
                        printBooks();
                        System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
                        sc.nextLine();
                    }
                    case 3 -> {
                        showavailableBook();
                    }
                    case 4 -> {
                        borrowBooks();
                    }
                    case 5 -> {
                        returnBooking();
                    }
                    case 6 -> {
                        System.out.println(GREEN + "GOOD BYE HAVE A GOOD TIME :)" + RESET);
                        System.exit(0);

                    }
                    default -> System.out.println(RED + "Input the valid option please  1-6 " + RESET);
                }
            } else System.out.println(RED + "Invalid Only degit are allowed." + RESET);
        } while (condition);
    }

}