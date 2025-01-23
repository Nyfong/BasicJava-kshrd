package practice1;

import java.util.Scanner;

public class BusBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean installChair = true;
        int tableNumber = 0;
        String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
        while (installChair) {
            System.out.println("========== SET UP CHAIR IN BUS ==========");
            System.out.print("-> Enter the seat of the bus between [25 - 45]:");
            tableNumber = sc.nextInt();
            if (tableNumber >= 25 && tableNumber <= 45) installChair = false;
            else System.out.println(RED + " Please enter the seat of the bus between [25 - 45] !!!" + RESET);
        }
        int tableArrBus[] = new int[tableNumber];
        String input = "";
        System.out.println("\n");
        int row = 0, inStock = 0, outStock = 0, chairNumber, inStockCount = 0, outStockCount = 0;
        boolean outerLoop = true;
        do {
            System.out.print("\n---------- DISPLAY BUS INFORMATION ----------");
            for (int i = 0; i < tableArrBus.length; i += 1) {
                if (row % 5 == 0)
                    System.out.println(" ");
                //print as a row
                System.out.print((tableArrBus[i] == 0) ? (GREEN + "(+)" + (i + 1) + "\t" + RESET) : (RED + "(-)" + (i + 1) + "\t") + RESET);
                // for row
                row += 1;
                //inStock Seat
                if (tableArrBus[i] == 0)
                    inStockCount++;
                inStock = inStockCount;
                //outStock Seat
                if (tableArrBus[i] != 0)
                    outStockCount++;
                outStock = tableNumber - inStockCount;
            }
            System.out.println("\n" + outStock + RED + "(-)Unavaiable," + RESET + inStock + GREEN + " (+)Avaiable" + RESET);
            System.out.println("");
            //RESET
            inStock = 0;
            outStock = 0;
            inStockCount = 0;
            outStockCount = 0;
            //for Enter_again
            boolean conditionY = true;
            //whileLoop1
            while (conditionY) {
                System.out.print("Do you want to booking the chair (Y/N): ");
                String bookngChair = sc.next();
                sc.nextLine();
                if (bookngChair.equalsIgnoreCase("Y")) {
                    conditionY = false;
                    //condition for busNumber
                    boolean coditionBusNumber = true;
                    //whileLoop2
                    while (coditionBusNumber) {
                        System.out.print("Enter chair number to booking: ");
                        input = sc.nextLine(); // Read input as a string
                        if (input.matches("\\d+")) { // \\d+ = one or more digits
                            chairNumber = Integer.parseInt(input); // Convert to integer
                            // Validation: valid userInput size
                            if (chairNumber <= tableArrBus.length && chairNumber > 0) {
                                // Check if the chair is booked
                                if (tableArrBus[chairNumber - 1] == 1) {
                                    System.out.println(RED + "-> Duplicate Please input again !!" + RESET);
                                    coditionBusNumber = true;
                                } else {
                                    coditionBusNumber = false;
                                    // chair again ?
                                    boolean coditionInputChairNumberAgain = true;
                                    //whileLoop3
                                    while (coditionInputChairNumberAgain) {
                                        System.out.print("Do you want to Book the chair number again? (Y/N): ");
                                        bookngChair = sc.next();

                                        if (bookngChair.equalsIgnoreCase("Y")) {
                                            coditionInputChairNumberAgain = false;
                                            System.out.println("[+ ** ]The seat is updated *");
                                            tableArrBus[chairNumber - 1] = 1; // Mark the chair as booked
                                        } else if (bookngChair.equalsIgnoreCase("N")) {
                                            System.out.println("Thank you, Good Luck !!");
                                            coditionInputChairNumberAgain = false;
                                        } else {
                                            System.out.println(RED + "Wrong input. Please input only (Y/N)" + RESET);
                                        }
                                    }
                                }
                            } else {
                                System.out.println(RED + "Wrong input. Please input only [1 - " + tableNumber + "]" + RESET);
                            }
                        } else {
                            System.out.println(RED + "Invalid input. Please enter a valid integer." + RESET);
                        }
                    }
                } else if (bookngChair.equalsIgnoreCase("n")) {
                    System.out.println("Thank you, Good Luck !! ");
                    outerLoop = false;
                    return;
                } else System.out.println(RED + "Wrong input. please Input only(Y/N)" + RESET); // when wrong input
            }
        } while (outerLoop);
        sc.close();
    }
}
