package practice2;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Arrays;
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
        boolean condition = true;
        //initilized the arary
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
                            if (seatNumber > 19) {
                                System.out.println(GREEN + "adding seat success" + RESET);
                                cinmaStorage = new int[hallNumber][seatNumber];
                                settingUpCinema = false;
                            } else {
                                System.out.println(RED + "The seat is more than or equal to 20 :)" + RESET);
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
                        System.out.println("Do you want to insert more movie? (Y/N)");
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            if (increase >= cinmaStorage.length) {
                                System.out.println(RED + "Overload the Storage of Hall :)" + RESET);
                                reuseAble = false;
                            } else {
                                reuseAble = true;
                            }
                        } else {

                            reuseAble = false;
                        }
                    }
                }
                case 2 -> {
                    outerLoop:
                    for (String[] strings : movieStorage) {
                        for (int y = 0; y < strings.length; y += 1) {
                            if (strings[y] != null) {
                                // display movie
                                String[] headers = {"ID", "Movie", "Type", "Duration", "Hall", "Seat", "Available"};
                                //for case 2 ng ha
                                int optionCase2, movieId;
                                System.out.println("Display all movie");
                                Table table = new Table(headers.length, BorderStyle.CLASSIC, ShownBorders.ALL, false);
                                CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                                // Add headers to the table
                                for (String header : headers) {
                                    table.addCell(header, cellStyle);
                                }

                                //  data rows to the table
                                for (int i = 0; i < movieStorage.length; i++) {
                                    if (movieStorage[i][0] == null) {
                                        continue; // Skip this iteration and move to the next row
                                    }
                                    table.addCell(String.valueOf(i + 1), cellStyle); // ID
                                    table.addCell((movieStorage[i][0] == null ? (RED + "No movie show" + RESET) : (GREEN + movieStorage[i][0] + RESET)), cellStyle);    // Movie
                                    table.addCell((movieStorage[i][1] == null ? (RED + "No type show" + RESET) : (GREEN + movieStorage[i][1] + RESET)), cellStyle);    // Type
                                    table.addCell((movieStorage[i][2] == null ? (RED + "No duration show" + RESET) : (GREEN + movieStorage[i][2] + RESET)), cellStyle);    // Duration
                                    table.addCell(String.valueOf((i + 1)), cellStyle);    // Hall
                                    table.addCell(movieStorage[i][4], cellStyle);    // Seat
                                    table.addCell(movieStorage[i][5], cellStyle);    // Available
                                }
                                // Print the table
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
                                                                if (movieId > 0 && movieId <= cinmaStorage.length) {
                                                                    if (cinmaStorage[movieId - 1] != null) {
                                                                        int row = 0;
                                                                        //a
                                                                        System.out.println("Title: " + movieStorage[movieId - 1][0] + "\t\t Hall ID #" + movieId);
                                                                        System.out.println("\t\t---------- Screen ---------- ");
                                                                        for (int i = 0; i < cinmaStorage[movieId - 1].length; i++) {
                                                                            if (row % 5 == 0) System.out.println();
                                                                            if (cinmaStorage[movieId - 1].length != 0) {
                                                                                System.out.print(cinmaStorage[movieId - 1][i] != 0 ? (RED + "[" + (i + 1) + "] Unavailable seat\t" + RESET) : (GREEN + "[" + (i + 1) + "] Available Seat\t" + RESET));
                                                                            }
                                                                            row++;
                                                                        }
                                                                        System.out.println();
                                                                        //a
                                                                        reuseAble = true;
                                                                        while (reuseAble) {
                                                                            System.out.println("\n[1]. Booking Ticket\t[2]. Back");
                                                                            input = sc.nextLine();
                                                                            if (input.matches("\\d+")) {
                                                                                int optionCaseBooking = Integer.parseInt(input);
                                                                                int count = 0;
                                                                                boolean agin = true;
                                                                                if (optionCaseBooking == 1) {

                                                                                    while (agin) {
                                                                                        System.out.print("\nChoose seat you want to Booking (e.g., 1,2,3,4..): ");
                                                                                        input = sc.nextLine();
                                                                                        if (input.matches("^(\\d+\\s*,\\s*)*\\d+$")) {
                                                                                            agin = false;
                                                                                            String[] numberStrings = input.split(",");
                                                                                            int[] numbers = new int[numberStrings.length];
                                                                                            for (int i = 0; i < numberStrings.length; i++) {
                                                                                                numbers[i] = Integer.parseInt(numberStrings[i].trim());
                                                                                                count += 1;
                                                                                            }

                                                                                            //System.out.println("Seats booked: " + java.util.Arrays.toString(numbers));
                                                                                            System.out.println(Arrays.toString(numbers));
                                                                                            System.out.println(movieId);

                                                                                            if (numbers.length < cinmaStorage[movieId - 1].length) {
                                                                                                for (int dataBook = 0; dataBook <= cinmaStorage[movieId - 1].length; dataBook += 1) {
                                                                                                    for (int dataTaken : numbers) {
                                                                                                        if (dataTaken == dataBook) {

                                                                                                            if (cinmaStorage[movieId - 1][dataBook - 1] == dataTaken) {
                                                                                                                System.out.println("The seat has been booked");
                                                                                                                agin = true;
                                                                                                            } else {
                                                                                                                cinmaStorage[movieId - 1][dataBook - 1] = dataTaken;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } else
                                                                                                System.out.println(RED + "enter agin please" + RESET);
                                                                                        } else {
                                                                                            System.out.println("Please enter example (1,2,3,4,5)");
                                                                                            agin = true;
                                                                                        }
                                                                                    }

                                                                                    System.out.println(Arrays.deepToString(cinmaStorage));
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
                                                                        reuseAble = true;
                                                                        System.out.println(RED + "There is no move show" + RESET);
                                                                    }
                                                                } else {
                                                                    System.out.println("Please input a valid movie ID.");
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

                                totalBooked++;

                        }

                        // If hall has bookings, show the information
                        if (totalBooked > 0) {
                            System.out.println("\n============================");
                            System.out.println("Hall #" + (hall + 1));
                            System.out.println("Movie: " + movieStorage[hall][0]);
                            System.out.println("Type: " + movieStorage[hall][1]);
                            System.out.println("Duration: " + movieStorage[hall][2] + " minutes");
                            System.out.println("============================");
                        }
                    }

                    if (!hasAnyBookings) {
                        System.out.println(RED + "No tickets have been booked yet." + RESET);
                    }

                    System.out.println("\nPress Enter to continue...");
                    sc.nextLine();
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

