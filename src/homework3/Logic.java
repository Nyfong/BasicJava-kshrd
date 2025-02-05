package homework3;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.GregorianCalendar;
import java.util.Calendar;

import java.util.Scanner;

public class Logic extends Book {
    Scanner sc = new Scanner(System.in);
    public String userName;
    public String libraryAddress;
    public int option;
    public Book[] books;
    public String input;
    public String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
    public boolean isValid = false; // Controls the loop
    public int indexSetRow = 0;

    public Logic(Book[] books) {
        super();
        this.books = books;
        initializeBooks();
    }

    private void initializeBooks() {
        books[0] = new Book(1, "The Road", new Author("Cormac McCarthy", "1933-2023"), "2006", null);
        books[1] = new Book(2, "The Night Watch", new Author("Sergei Lukyanenko", "1968-2002"), "1998", null);
        books[2] = new Book(3, "The Goldfinch", new Author("Donna Tartt", "1963-2000"), "2013", "a");
        books[3] = new Book(4, "The Testaments", new Author("Margaret Atwood", "1939-1999"), "2019", null);
    }


    String[] headers = {"ID", "Book", "Author", "When", "Available"};

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

    //for helping insert auto Id
    private int countNotNullStorageAndGetId() {
        int bookId = 0;
        for (int i = 0; i < books.length; i += 1) {
            if (books[i] != null) {
                if (books[i].getId() != 0) {
                    bookId = books[i].getId();
                }
            }
        }
        return bookId;
    }

    //for setRow by number
    private void setRowByNumber(int rowNumber) {
        System.out.println(rowNumber != 0 ? ("You haved show " + countNotNullStorage()) : ("You haved show " + rowNumber + "\t and Total row is :" + countNotNullStorage()));
        Table table = new Table(headers.length, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL, false);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        for (String header : headers) {
            table.addCell(header, cellStyle);
        }
        int i = 0;
        int j = 0;
        int idCount = 0;
        for (i = 0; i < rowNumber; i += 1) {
            if (books[i] != null) {
                table.addCell((books[j].getStatus() == null ? (books[i].getId() + "") : ((books[j].getStatus().equalsIgnoreCase("REMOVE")) ? (RED + "REMOVE" + RESET) : (books[i].getId() + ""))), cellStyle); // Type
                table.addCell(String.valueOf(books[j].getTitle()), cellStyle); // Type
                table.addCell(books[j].getAuthor().getName() + "(" + books[j].getAuthor().getYearsActive() + ")", cellStyle); // Type
                table.addCell(String.valueOf(books[j].getPublishYear()), cellStyle); // Type
                table.addCell((books[j].getStatus() == null ? (GREEN + "Available" + RESET) : ((books[j].getStatus().equalsIgnoreCase("REMOVE")) ? (RED + "REMOVE" + RESET) : (RED + "Unavailable" + RESET))), cellStyle); // Type
            }
            j += 1;
            idCount += 1;

        }
        System.out.println(table.render());
    }

    //case 1
    private void addBook() {
        int bookId = 0;
        String bookName, authorName;
        Author auth = new Author();
        Scanner sc = new Scanner(System.in);  // Make sure to create a Scanner object
        System.out.println("========= ADD BOOK INFO =========");
        bookId = countNotNullStorageAndGetId() + 1;
        this.setId(bookId);
        if (getId() == books.length) {
            System.out.println(RED + "Full of Storage" + RESET);
            System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
            return;
        }
        System.out.println("Current Book Id:" + (getId()));
        System.out.println("last bookId was:" + countNotNullStorageAndGetId());
        // Reset isValid for next loop
        isValid = false;
        // Asking for Book Name
        while (!isValid) {
            System.out.print("=> Book Name : ");
            String input = sc.nextLine();
            if ( input.isBlank()) {
                System.out.println(RED + "Book Name cannot be empty or contain only spaces." + RESET);
                continue;
            }
            if (input.length() > 45) { // YYYY-YYYY = 9 characters
                System.out.println(RED + "Invalid input: too long." + RESET);
                continue;
            }
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
            if ( input.isBlank()) {
                System.out.println(RED + "Author Name cannot be empty or contain only spaces." + RESET);
                continue;
            }
            if (input.length() > 40) { // YYYY-YYYY = 9 characters
                System.out.println(RED + "Invalid input: too long." + RESET);
                continue;
            }
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
        // Validate Author's Active Years
        isValid = false;
        while (!isValid) {
            System.out.print("=> Author Year Active: ");
            String input = sc.nextLine();

            // Split years and validate format
            if (!input.matches("[0-9]{4}-[0-9]{4}")) {
                System.out.println(RED + "Invalid format. Use YYYY-YYYY format." + RESET);
                continue;
            }

            String[] years = input.split("-");
            int startYear = Integer.parseInt(years[0]);
            int endYear = Integer.parseInt(years[1]);

            if (startYear > endYear) {
                System.out.println(RED + "Start year cannot be greater than end year." + RESET);
                continue;
            }

            if (endYear - startYear < 10) {
                System.out.println(RED + "Author must be active for at least 10 years." + RESET);
                continue;
            }

            auth.setYearActive(input);
            System.out.println("The author was active from " + auth.getYearsActive());
            isValid = true;
        }

        // Validate Published Year
        isValid = false;
        while (!isValid) {
            System.out.print("=> Published Year: ");
            String input = sc.nextLine();

            if (!input.matches("\\d{4}")) {
                System.out.println(RED + "Invalid year. Use YYYY format." + RESET);
                continue;
            }

            int publishYear = Integer.parseInt(input);
            String[] activeYears = auth.getYearsActive().split("-");
            int startActive = Integer.parseInt(activeYears[0]);
            int endActive = Integer.parseInt(activeYears[1]);

            // Check if publish year is within active years
            if (publishYear <= startActive || publishYear >= endActive) {
                System.out.println(RED + "Published year must be between " + startActive + " and " + endActive + RESET);
                continue;
            }

            this.setPublishYear(input);
            System.out.println("The book was published in " + input);
            isValid = true;
        }
        setStatus(null);
        if (this.getId() < books.length) { // Ensure index is within bounds
            books[countNotNullStorage()] = new Book(
                    this.getId(),                      // Assuming `getId()` gives a valid index
                    this.getTitle(),
                    new Author(this.getAuthor().getName(), this.getAuthor().getYearsActive()), // Create Author object correctly
                    this.getPublishYear(),
                    getStatus()
            );
        } else {
            System.out.println("Error: Book ID is out of bounds!");
        }
        // Displaying Success Message
        System.out.println("Book is added successfully ✅");
        System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
    }

    //case 2
    private void printBooks() {
        if (indexSetRow > 0) {
            setRowByNumber(indexSetRow);
        } else setRowByNumber(books.length);
        System.out.println("1. Next Page\t2. Previous Page\t\t3. First Page\t4. Last Page\t5. Exit");
    }

    //case 3
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
            if (books[i] != null && books[i].getStatus() == null) {
                table.addCell("" + (books[j].getId()), cellStyle); // Type
                table.addCell(String.valueOf(books[j].getTitle()), cellStyle); // Type
                table.addCell(books[j].getAuthor().getName() + "(" + books[j].getAuthor().getYearsActive() + ")", cellStyle); // Type
                table.addCell(String.valueOf(books[j].getPublishYear()), cellStyle); // Type
                table.addCell((books[j].getStatus() == null ? av : ""), cellStyle); // Type
            }
            j += 1;
            idCount += 1;
        }
        System.out.println(table.render());
    }

    //case 4
    //borrowBook
    private void borrowBooks() {
        int borrowBookId;
        do {
            System.out.print("-> Enter book Id to borow:");
            input = sc.nextLine();
            if (input.length() > 3) {
                System.out.println(RED + "Invalid input: too long." + RESET);
                continue;
            } else {
                if (input.matches("^[1-9]\\d*$")) {
                    borrowBookId = Integer.parseInt(input);
                    int bookId = 0;
                    for (int i = 0; i < books.length; i += 1) {
                        if (books[i] != null) {
                            if (books[i].getId() == borrowBookId) {
                                bookId = i;
                                break;
                            }
                            if (books[i].getId() != borrowBookId) {
                                bookId = 10000;
                            }

                        }
                    }
                    //System.out.println("found too but no ops" + bookId);
                    //System.out.println("Book id found" + (bookId - 1));

                    //switch to book id
                    if (bookId > books.length || bookId >= (countNotNullStorage() + 1)) {
                        System.out.println(RED + "Input the valid id please " + RESET);
                        isValid = false;
                    } else if (books[bookId].getStatus() != null) {
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
                        } else {
                            isValid = false;
                        }
                    } else {
                        int i = 0;
                        int j = 0;
                        int idCount = 0;
                        books[bookId].setStatus("Aviable");
                        System.out.println("Book Id: " + (borrowBookId));
                        System.out.println("Book Title: " + (books[bookId].getTitle()));
                        System.out.println("Book Author: " + (books[bookId].getAuthor().getName() + " (" + books[bookId].getPublishYear() + ")"));
                        System.out.println("Book Publishyear: " + (books[bookId].getAuthor().getYearsActive()));

                        System.out.println(GREEN + ">>>>>>>>>>>> Booking succus <<<<<<<<<<<<" + RESET);
                        System.out.println("");
                        isValid = true;


                    }
                } else {
                    System.out.println(RED + "Input only degit and positive " + RESET);
                    isValid = false;
                }
            }

        } while (!isValid);

    }

    //case 5
    private void returnBooking() {
        int returnBookId;
        do {
            System.out.print("-> Enter book Id to return:");
            input = sc.nextLine();
            if (input.length() > 3) { // YYYY-YYYY = 9 characters
                System.out.println(RED + "Invalid input: too long." + RESET);
                continue;
            } else {
                if (input.matches("^[1-9]\\d*$")) {
                    returnBookId = Integer.parseInt(input);
                    int bookId = 0;
                    for (int i = 0; i < books.length; i += 1) {
                        if (books[i] != null) {
                            if (books[i].getId() == returnBookId) {
                                bookId = i;
                                break;
                            }
                            if (books[i].getId() != returnBookId) {
                                bookId = 10000;
                            }

                        }
                    }


                    //switch to book id
                    if (bookId > books.length || bookId >= (countNotNullStorage() + 1)) {
                        System.out.println(RED + "Input the valid id please " + RESET);
                        isValid = false;
                    } else if (books[bookId].getStatus() == null) {
                        System.out.println(GREEN + "Book is Aviable" + RESET);
                        System.out.println(RED + "BookiD [" + returnBookId + "] can not return" + RESET);
                        System.out.println("");
                        System.out.print("Do you want to return again [Y / N ]? :");
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            isValid = false;
                        } else if (input.equalsIgnoreCase("n")) {
                            System.out.println(GREEN + "Thank you :)" + RESET);
                            isValid = true;
                            return;
                        } else {
                            isValid = false;
                        }
                    } else {
                        int i = 0;
                        int j = 0;
                        int idCount = 0;
                        books[bookId].setStatus(null);
                        System.out.println("Book Id: " + (returnBookId));
                        System.out.println("Book Title: " + (books[bookId].getTitle()));
                        System.out.println("Book Author: " + (books[bookId].getAuthor().getName() + " (" + books[bookId].getPublishYear() + ")"));
                        System.out.println("Book Publishyear: " + (books[bookId].getAuthor().getYearsActive()));

                        System.out.println(GREEN + ">>>>>>>>>>>> Returning succus <<<<<<<<<<<<" + RESET);
                        System.out.println("");
                        isValid = true;


                    }
                } else {
                    System.out.println(RED + "Input only degit and positive " + RESET);
                    isValid = false;
                }
            }

        } while (!isValid);
    }

    //case 6 set row
    private void setRowToShowRecord() {
        int setRowRecord;
        int returnBookId;
        do {
            System.out.print("-> Enter number of row to set row record:");
            input = sc.nextLine();
            if (input.length() > 3) {
                System.out.println(RED + "Invalid input: too long." + RESET);
                isValid = false;
            } else {
                if (input.matches("^[1-9]\\d*$")) {
                    setRowRecord = Integer.parseInt(input);
                    int bookId = 0;
                    for (int i = 0; i < books.length; i += 1) {
                        if (books[i] != null) {
                            if (books[i].getId() == setRowRecord) {
                                bookId = i;
                                break;
                            }
                            if (books[i].getId() != setRowRecord) {
                                bookId = 10000;
                            }
                        }
                    }
                    //switch to book id
                    if (bookId > books.length || bookId >= (countNotNullStorage() + 1)) {
                        System.out.println(RED + "Input the valid id please " + RESET);
                        isValid = false;

                    } else {
                        System.out.println(GREEN + ">>>>>>>>>>>> Reset Row succus <<<<<<<<<<<<" + RESET);
                        // setRow
                        indexSetRow = setRowRecord;
                        isValid = true;
                    }
                } else {
                    System.out.println(RED + "Input only degit and positive " + RESET);
                    isValid = false;
                }
            }

        } while (!isValid);
    }

    //case remove 7  removeBook
    private void removeBook() {
        int removebookId;
        int returnBookId;
        do {
            System.out.print("\n-> Enter book Id to Delete:");
            input = sc.nextLine();
            if (input.length() > 3) {
                System.out.println(RED + "Invalid input: too long." + RESET);
                isValid = false;
            } else {
                if (input.matches("^[1-9]\\d*$")) {
                    removebookId = Integer.parseInt(input);
                    int bookId = 0;
                    for (int i = 0; i < books.length; i += 1) {
                        if (books[i] != null) {
                            if (books[i].getId() == removebookId) {
                                bookId = i;
                                break;
                            }
                            if (books[i].getId() != removebookId) {
                                bookId = 10000;
                            }
                        }
                    }
                    //switch to book id
                    if (bookId > books.length || bookId >= (countNotNullStorage() + 1)) {
                        System.out.println(RED + "Input the valid id please " + RESET);
                        isValid = false;

                    } else if (books[bookId].getStatus() != null) {
                        System.out.println(RED + "Already book" + RESET);
                        System.out.println(RED + "BookiD [" + removebookId + "] is Unaviable" + RESET);
                        System.out.println("");
                        boolean condi = true;
                        while (condi) {
                            System.out.print("Do you want to remove again [Y / N ]? :");
                            input = sc.nextLine();
                            if (input.equalsIgnoreCase("y")) {
                                isValid = false;
                                condi = false;
                            } else if (input.equalsIgnoreCase("n")) {
                                System.out.println(GREEN + "Thank you :)" + RESET);
                                isValid = true;
                                return;
                            } else {
                                System.out.println(GREEN + "Enter only  Y or N :)" + RESET);
                                isValid = false;
                            }
                        }
                    } else {
                        int i = 0;
                        int j = 0;
                        int idCount = 0;
                        books[bookId].setStatus("remove");
                        System.out.println("Book Id: " + (removebookId));
                        System.out.println("Book Title: " + (books[bookId].getTitle()));
                        System.out.println("Book Author: " + (books[bookId].getAuthor().getName() + " (" + books[bookId].getPublishYear() + ")"));
                        System.out.println("Book Publishyear: " + (books[bookId].getAuthor().getYearsActive()));

                        System.out.println(GREEN + ">>>>>>>>>>>> remove succus <<<<<<<<<<<<" + RESET);
                        System.out.println("");
                        int removeIndex = bookId;
                        books[bookId] = new Book(removebookId, (RED + "REMOVE" + RESET), new Author((RED + "REMOVE" + RESET), (RED + "REMOVE" + RESET)), (RED + "REMOVE" + RESET), "REMOVE");
                        isValid = true;

                    }
                } else {
                    System.out.println(RED + "Input only degit and positive " + RESET);
                    isValid = false;
                }
            }

        } while (!isValid);

    }

    //method for setup Library
    private void setUpLibrary() {
        Calendar c = new GregorianCalendar();
        System.out.println(RESET + "=================== SET UP LIBRARY ===================" + RESET);
        // Validate Library's Name
        while (!isValid) {
            System.out.print("Enter Library's Name: ");
            userName = sc.nextLine();
            if (userName.isBlank()) {
                System.out.println(RED + "Name cannot be empty or contain only spaces." + RESET);
            } else if (!userName.matches("[a-zA-Z ]+")) { // Only allows letters
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
            if (libraryAddress.isBlank()) {
                System.out.println(RED + "Adress cannot be empty or contain only spaces." + RESET);
            }
            else if (!libraryAddress.matches("[a-zA-Z ]+")) { // Only allows letters
                System.out.println(RED + "Invalid address. Only letters are allowed." + RESET);
            } else {
                isValid = true; // Exit loop
            }
        }
        System.out.println("Hello  \" " + GREEN + this.userName.toUpperCase() + RESET + " \" Library is already created in [ " + GREEN + this.libraryAddress.toUpperCase() + RESET + " ] address successfully on " + c.getTime());

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
            System.out.println("6- Set row to show record");
            System.out.println("7- Remove Book");
            System.out.println("8- Exit");
            System.out.println("-----------------------------------------");
            System.out.print("=> Choose option(1-7): ");
            input = sc.nextLine();
            if (input.length() > 9) { // YYYY-YYYY = 9 characters
                System.out.println(RED + "Invalid input: too long." + RESET);
                continue;
            }
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
                        setRowToShowRecord();
                    }
                    case 7 -> {
                        removeBook();
                    }
                    case 8 -> {
                        System.out.println(GREEN + "(^-^) Good Bye! (^-^)" + RESET);
                        System.exit(0);

                    }
                    default -> System.out.println(RED + "Input the valid option please  1-6 " + RESET);
                }
            } else System.out.println(RED + "Invalid Only degit are allowed." + RESET);
        } while (condition);
    }
}