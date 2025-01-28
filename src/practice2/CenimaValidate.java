package practice2;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;

public class CenimaValidate {
    final static int TEST_ROW = 3;
    final static int TEST_COL = 3;
    //final static int MOVIE_SAMPLE = 3;
    public static void main(String[] args) {
        //scanner
        Scanner sc = new Scanner(System.in);
        String input;
        int increase = 0;
        int hallNumber = 0, seatNumber;
        int optionSetRow = 0;
        boolean condition = true;
        //initialized the array
        int[][] cinmaStorage = new int[TEST_ROW][TEST_COL];
        String[][] movieStorage = new String[TEST_ROW][6];
        String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
        boolean settingUpCinema = true, reuseAble = true;
        //checking setting up seat
        while (settingUpCinema) {
            System.out.println("---------- Setting up the Cinema ---------- ");
            System.out.print("Enter the number of the hall: ");
            input = sc.nextLine();
            if (input.matches("\\d+")) {
                hallNumber = Integer.parseInt(input);
                if (hallNumber > 0) {
                    while (settingUpCinema) {
                        System.out.print("Enter the number of the seat in each hall: ");
                        input = sc.nextLine();
                        if (input.matches("\\d+")) {
                            seatNumber = Integer.parseInt(input);
                            if (seatNumber > 49) {
                                System.out.println(GREEN + "adding seat success" + RESET);
                                cinmaStorage = new int[hallNumber][seatNumber];
                                settingUpCinema = false;
                            } else {
                                System.out.println(RED + "The seat is more than or equal to 50 :)" + RESET);
                            }
                        } else System.out.println(RED + "The hall is grater than 20" + RESET);

                    }
                } else System.out.println(RED + "The hall is grater than 0" + RESET);
            } else System.out.println(RED + "Please input only digit :)" + RESET);
            movieStorage = new String[hallNumber][6];
        }
        //display menu
        do {
            int option;
            System.out.println("---------- Movie management System ---------- ");
            System.out.println("[1]. Insert Movie");
            System.out.println("[2]. Check and Booking");
            System.out.println("[3]. Check Ticket");
            System.out.println("[4]. Reset Hall");
            System.out.println("[5]. Reset row");
            System.out.println("[6]. Exit");
            System.out.print("--> Chose any option (1-5) : ");
            input = sc.nextLine();
            while (!input.matches("\\d+")) {
                System.out.println(RED + "Invalid option. Please choose a number between 1 and 6." + RESET);
                System.out.print("--> Choose any option (1-6) : ");
                input = sc.nextLine();
            }
            option = Integer.parseInt(input);
            switch (option) {
                case 1 -> {
                    String movieName, movieType = "";
                    int setHour = 0;
                    reuseAble = true; // Reset reuseAble to true
                    if (increase >= cinmaStorage.length) {
                        System.out.println(RED + "All halls are already occupied. Cannot add more movies." + RESET);
                        continue;
                    }
                    System.out.println("---------- Insert Movie Information ---------- ");
                    while (reuseAble) {
                        System.out.println("->Enter movie name");
                        movieName = sc.nextLine();
                        //movie type
                        while (reuseAble) {
                            System.out.println("->Enter movie Type");
                            input = sc.nextLine();
                            if (input.matches("[a-zA-Z]+")) {
                                movieType = input;
                                while (reuseAble) {
                                    System.out.println("->Enter Duration (min) 60- 180");
                                    input = sc.nextLine();
                                    if (input.matches("\\d+")) {
                                        setHour = Integer.parseInt(input);
                                        if (setHour >= 60 && setHour <= 180) {
                                            reuseAble = false;
                                        } else System.out.println("please enter valid minutes :)");
                                    } else System.out.println(RED + "Please input invalid hours :) " + RESET);
                                }
                            } else System.out.println(RED + "Please input invalid type :) " + RESET);
                        }
                        //initialized
                        movieStorage[increase][0] = movieName;
                        movieStorage[increase][1] = movieType;
                        movieStorage[increase][2] = String.valueOf(setHour);
                        increase += 1;
                        System.out.println(GREEN + movieName + RESET + " will show in Hall #" + increase);
                        boolean askingAgain = true;
                        while (askingAgain) {
                            System.out.println("Do you want to insert more movie? (Y/N)");
                            input = sc.nextLine();
                            if (input.equalsIgnoreCase("y")) {
                                if (increase >= cinmaStorage.length) {
                                    System.out.println(RED + "Overload the Storage of Hall :)" + RESET);
                                    reuseAble = false;
                                    askingAgain = false;
                                } else {
                                    reuseAble = true;
                                    askingAgain = false;
                                }
                            } else if (input.equalsIgnoreCase("N")) {
                                System.out.println(RED + "Thank :)" + RESET);
                                askingAgain = false;
                            } else {
                                askingAgain = true;
                            }
                        }
                    }
                }
                case 2 -> {
                    int toBreakloop = 0;
                    outerLoop:
                    for (String[] strings : movieStorage) {
                        for (int y = 0; y < strings.length; y += 1) {
                            if (strings[toBreakloop] != null) {
                                //for case 2 ng ha
                                int optionCase2, movieId;
                                System.out.println("Display all movie");
                                String[] headers = {"ID", "Movie", "Type", "Duration", "Hall", "Seat", "Available"};
                                //pkert table rg
                                Table table = new Table(headers.length, BorderStyle.CLASSIC, ShownBorders.ALL, false);
                                CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

                                //display header rg
                                for (String header : headers) {
                                    table.addCell(header, cellStyle);
                                }
                                if (optionSetRow > 0) {
                                    for (int hall = 0; hall < optionSetRow; hall++) {
                                        // Skip if no movie in this hall
                                        if (movieStorage[hall][0] == null) {
                                            continue;
                                        }
                                        // Count booked seats
                                        int totalBooked = 0;
                                        for (int seat = 0; seat < cinmaStorage[hall].length; seat++) {
                                            if (cinmaStorage[hall][seat] != 0) {
                                                totalBooked++;
                                            }
                                        }
                                        // Add data rows to the table
                                        table.addCell(String.valueOf(hall + 1), cellStyle); // ID
                                        table.addCell(movieStorage[hall][0], cellStyle); // Movie
                                        table.addCell(movieStorage[hall][1], cellStyle); // Type
                                        table.addCell(movieStorage[hall][2], cellStyle); // Duration
                                        table.addCell(String.valueOf(hall + 1), cellStyle); // Hall
                                        table.addCell(String.valueOf(cinmaStorage[hall].length), cellStyle); // Total Seats
                                        table.addCell(String.valueOf(cinmaStorage[hall].length - totalBooked), cellStyle); // Available Seats
                                    }
                                } else {
                                    for (int hall = 0; hall < cinmaStorage.length; hall++) {
                                        // Skip if no movie in this hall
                                        if (movieStorage[hall][0] == null) {
                                            continue;
                                        }
                                        // Count booked seats
                                        int totalBooked = 0;
                                        for (int seat = 0; seat < cinmaStorage[hall].length; seat++) {
                                            if (cinmaStorage[hall][seat] != 0) {
                                                totalBooked++;
                                            }
                                        }
                                        // Add data rows to the table
                                        table.addCell(String.valueOf(hall + 1), cellStyle); // ID
                                        table.addCell(movieStorage[hall][0], cellStyle); // Movie
                                        table.addCell(movieStorage[hall][1], cellStyle); // Type
                                        table.addCell(movieStorage[hall][2], cellStyle); // Duration
                                        table.addCell(String.valueOf(hall + 1), cellStyle); // Hall
                                        table.addCell(String.valueOf(cinmaStorage[hall].length), cellStyle); // Total Seats
                                        table.addCell(String.valueOf(cinmaStorage[hall].length - totalBooked), cellStyle); // Available Seats
                                    }
                                }
                                // print the table
                                System.out.println(table.render());

                                do {
                                    System.out.println("[1]. Booking movie \t[2]. First\t[3]. Next\t[4]. Previous\t[5]. Last\t[6]. Exit ");
                                    input = sc.nextLine();
                                    if (input.matches("\\d+")) {
                                        optionCase2 = Integer.parseInt(input);
                                        if (optionCase2 < 1 || optionCase2 > 6) {
                                            System.out.println(RED + "Invalid option. Please choose a valid option (1-6)." + RESET);
                                            continue;
                                        }
                                        switch (optionCase2) {
                                            case 1 -> {
                                                reuseAble = true;
                                                while (reuseAble) {
                                                    System.out.println("Enter movie ID");
                                                    input = sc.nextLine();
                                                    if (input.matches("\\d+")) {
                                                        movieId = Integer.parseInt(input);
                                                        //check is the movie exit at the hall
                                                        checkId:
                                                        if (movieId <= cinmaStorage.length && movieId > 0) {
                                                            if (movieStorage[movieId - 1][0] != null) {
                                                                {
                                                                    if (cinmaStorage[movieId - 1] != null) {
                                                                        int row = 0;
                                                                        //a
                                                                        System.out.println("Title: " + movieStorage[movieId - 1][0] + "\t\t Hall ID #" + movieId);
                                                                        System.out.println("\t\t---------- Screen ---------- ");
//
                                                                        // Create a table for the movie title and hall ID
                                                                        Table titleTable = new Table(1, BorderStyle.CLASSIC, ShownBorders.ALL, false);
                                                                        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);

                                                                        // Print the title table
                                                                        System.out.println(titleTable.render());

                                                                        // Create a table for the screen and seats
                                                                        Table seatTable = new Table(5, BorderStyle.CLASSIC, ShownBorders.ALL, false);
                                                                        CellStyle seatStyle = new CellStyle(CellStyle.HorizontalAlign.center);

                                                                        // Add header for the screen
                                                                        seatTable.addCell("---------- Screen ----------", seatStyle, 5);

                                                                        // Add seat availability to the table
                                                                        for (int i = 0; i < cinmaStorage[movieId - 1].length; i++) {
                                                                            if (cinmaStorage[movieId - 1][i] != 0) {
                                                                                seatTable.addCell(RED + "[" + (i + 1) + "] Unavailable seat" + RESET, seatStyle);
                                                                            } else {
                                                                                seatTable.addCell(GREEN + "[" + (i + 1) + "] Available Seat" + RESET, seatStyle);
                                                                            }
                                                                            row++;
                                                                        }

                                                                        // Print the seat table
                                                                        System.out.println(seatTable.render());

                                                                        System.out.println();
                                                                        //for option book and back
                                                                        while (reuseAble) {
                                                                            System.out.println("\n[1]. Booking Ticket\t[2]. Back");
                                                                            input = sc.nextLine();
                                                                            if (input.matches("\\d+")) {
                                                                                int optionCaseBooking = Integer.parseInt(input);
                                                                                boolean agin = true;
                                                                                if (optionCaseBooking == 1) {

                                                                                    while (agin) {
                                                                                        System.out.print("\nChoose seat you want to Booking (e.g., 1,2,3,4..): ");
                                                                                        input = sc.nextLine();
                                                                                        if (input.matches("^(?!.*\\b0\\b)\\s*([1-9]\\d*\\s*(,\\s*[1-9]\\d*)*)?\\s*$")) {
                                                                                            agin = false;
                                                                                            String[] numberStrings = input.split(",");
                                                                                            int[] numbers = new int[numberStrings.length];
                                                                                            for (int i = 0; i < numberStrings.length; i++) {
                                                                                                numbers[i] = Integer.parseInt(numberStrings[i].trim());
                                                                                            }

                                                                                            bookedSeat:
                                                                                            if (numbers.length < cinmaStorage[movieId - 1].length) {
                                                                                                for (int dataBook = 0; dataBook <= cinmaStorage[movieId - 1].length; dataBook += 1) {
                                                                                                    for (int dataTaken : numbers) {
                                                                                                        if (dataTaken == dataBook) {

                                                                                                            if (cinmaStorage[movieId - 1][dataBook - 1] == dataTaken) {
                                                                                                                System.out.println("The seat has been booked");
                                                                                                                agin = true;
                                                                                                                break bookedSeat;
                                                                                                            } else {
                                                                                                                cinmaStorage[movieId - 1][dataBook - 1] = dataTaken;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } else
                                                                                                System.out.println(RED + "enter again please" + RESET);
                                                                                        } else {
                                                                                            System.out.println("Please enter example (1,2,3,4,5)");
                                                                                        }
                                                                                    }
                                                                                    reuseAble = false;
                                                                                } else if (optionCaseBooking == 2) {
                                                                                    reuseAble = false;
                                                                                } else {
                                                                                    System.out.println("Invalid option. Please choose a valid option (1-2).");
                                                                                }
                                                                            } else {
                                                                                System.out.println("Invalid input. Please enter numbers only.");
                                                                            }
                                                                        }
                                                                    } else {
                                                                        System.out.println(RED + "There is no move show" + RESET);
                                                                    }
                                                                }
                                                            } else {
                                                                System.out.println(RED + "The hall is not yet upload movie" + RESET);
                                                                break checkId;
                                                            }
                                                        } else {
                                                            System.out.println(RED + "The hall has only #" + cinmaStorage.length + ":))" + RESET);
                                                        }
                                                    } else {
                                                        System.out.println(RED + "Invalid input. Please enter a number." + RESET);
                                                    }
                                                }
                                            }
                                            case 2 -> {
                                                System.out.println("2 First");
                                            }
                                            case 3 -> {
                                                System.out.println("3 Next");
                                            }
                                            case 4 -> {
                                                System.out.println("4 Prevous");
                                            }
                                            case 5 -> {
                                                System.out.println("5 Last");
                                            }
                                            case 6 -> {
                                                System.out.println("Thank you so much");
                                                break outerLoop;
                                            }
                                            // Add cases for 2, 3, 4, 5, and 6 as needed
                                            default ->
                                                    System.out.println("Invalid option. Please choose a valid option (1-6).");
                                        }
                                    } else {
                                        System.out.println("Invalid input. Please enter a number.");
                                    }
                                } while (reuseAble);
                            } else {
                                System.out.println(RED + "You can not book it please input movie first" + RESET);
                                System.out.println(">>>>>>>>>>> please press any key to continue <<<<<<<<<<");
                                sc.nextLine();

                                break outerLoop;
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("\n---------- Ticket Information ----------");
                    boolean hasAnyBookings = false;

                    // Check each hall
                    for (int hall = 0; hall < cinmaStorage.length; hall++) {
                        // Skip if no movie in this hall
                        if (movieStorage[hall][0] == null) {
                            continue;
                        }
                        String bookedSeats = "";
                        int totalBooked = 0;
                        // Count booked seats and create list of seat numbers
                        for (int seat = 0; seat < cinmaStorage[hall].length; seat++) {
                            if (cinmaStorage[hall][seat] != 0) {
                                bookedSeats += (bookedSeats.isEmpty() ? "" : ", ") + (seat + 1);
                                totalBooked++;
                                hasAnyBookings = true;
                            }
                        }
                        // If hall has bookings, show the information
                        if (totalBooked > 0) {
                            System.out.println("\n============================");
                            System.out.println("Hall #" + (hall + 1));
                            System.out.println("Movie: " + movieStorage[hall][0]);
                            System.out.println("Type: " + movieStorage[hall][1]);
                            System.out.println("Duration: " + movieStorage[hall][2] + " minutes");
                            System.out.println("Booked Seats: " + bookedSeats);
                            System.out.println("Available Seats: " + (cinmaStorage[hall].length - totalBooked));
                            System.out.println("============================");
                        }
                    }
                    if (!hasAnyBookings) {
                        System.out.println(RED + "No tickets have been booked yet." + RESET);
                    }
                    System.out.println("\nPress Enter to continue...");
                    sc.nextLine();
                }
                case 4 -> {
                    if (movieStorage[0][0] != null) {
                        endLoop:
                        do {
                            System.out.print("Are you sure you want to reset " + GREEN + "HALL" + RESET + " (Y/N) : ");
                            input = sc.nextLine();
                            if (input.equalsIgnoreCase("y")) {
                                for (int i = 0; i < cinmaStorage.length; i += 1) {
                                    for (int j = 0; j < cinmaStorage[i].length; j += 1) {
                                        cinmaStorage[i][j] = 0;
                                    }
                                }
                                System.out.println(GREEN + "all hall is already reset" + RESET);
                                System.out.println(GREEN + ">>>>>>>>>>>>>>> APPROVED <<<<<<<<<<<" + RESET);
                                reuseAble = false;
                                break endLoop;
                            } else if (input.equalsIgnoreCase("n")) {
                                System.out.println("your hall not yet reset Thank you");
                                break endLoop;
                            } else {
                                System.out.println("Enter again please");
                                reuseAble = true;
                            }

                        } while (reuseAble);
                    } else {
                        System.out.print(RED + "No movie yet." + RESET);
                        System.out.println("\nPress Enter to continue...");
                        sc.nextLine();
                    }
                }
                case 5 -> {
                    boolean checkingIsDegit = true;
                    if (movieStorage[0][0] != null) {

                        do {
                            // Prompt the user to enter the number of rows to display
                            System.out.print("Enter the number of rows to display (1, 2, etc.): ");
                            input = sc.nextLine();
                            if (input.matches("\\d+")) {
                                optionSetRow = Integer.parseInt(input);
                                if (optionSetRow > cinmaStorage.length || optionSetRow <= 0) {
                                    System.out.println("The movie is only" + movieStorage.length);
                                    System.out.println(RED + ">>>>>>>>>>>>>>> NOT APPROVED <<<<<<<<<<<" + RESET);
                                    optionSetRow = -1;
                                } else {
                                    endLoop:
                                    do {
                                        System.out.print("Are you sure you want to reset (Y/N) : ");
                                        input = sc.nextLine();
                                        if (input.equalsIgnoreCase("y")) {
                                            System.out.println(GREEN + ">>>>>>>>>>>>>>> APPROVED <<<<<<<<<<<" + RESET);
                                            reuseAble = false;
                                        } else if (input.equalsIgnoreCase("n")) {
                                            System.out.println("Thank you");
                                            optionSetRow = -1;
                                            break endLoop;
                                        } else {
                                            System.out.println(RED + "Enter again please" + RESET);
                                            reuseAble = true;
                                        }
                                    } while (reuseAble);
                                }
                                checkingIsDegit = false;
                            } else {
                                System.out.println(RED + "Enter again please" + RESET);
                            }
                        } while (checkingIsDegit);
                    } else {
                        System.out.print(RED + "No movie yet." + RESET);
                        System.out.println("\nPress Enter to continue...");
                        sc.nextLine();
                    }
                }
                case 6 -> {
                    System.out.println("Thank you for using the system");
                    condition = false;
                }
                default -> System.out.println(RED + "Invalid option. Please choose a valid option (1-6)." + RESET);
            }
        } while (condition);

    }
}

