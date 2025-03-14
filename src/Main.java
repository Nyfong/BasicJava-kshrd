//import java.util.Scanner;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    public static void main(String[] args) {
//        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");
//
//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
//        Scanner sc = new Scanner(System.in);
//        String input;
//        int chairNumber = 0;
//        boolean coditionBusNumber = true;
//        int[] tableArrBus = new int[25]; // Example array to represent chair availability
//        String bookngChair;
//        String RED = "\u001B[31m"; // ANSI color code for red
//        String RESET = "\u001B[0m"; // ANSI color code to reset color
//        int tableNumber = 25; // Example table number
//
//        while (coditionBusNumber) {
//            System.out.print("Enter chair number to booking: ");
//            input = sc.nextLine(); // Read input as a string
//
//            // Validate if the input is a valid integer using regex
//            if (input.matches("\\d+")) { // \\d+ means one or more digits
//                chairNumber = Integer.parseInt(input); // Convert to integer
//
//                // Validation: Check if chairNumber is within valid range
//                if (chairNumber <= tableArrBus.length && chairNumber > 0) {
//                    // Check if the chair is already booked
//                    if (tableArrBus[chairNumber - 1] == 1) {
//                        System.out.println(RED + "-> Duplicate Please input again !!" + RESET);
//                        coditionBusNumber = true;
//                    } else {
//                        coditionBusNumber = false;
//
//                        // Ask if the user wants to book the chair again
//                        boolean coditionInputChairNumberAgain = true;
//                        while (coditionInputChairNumberAgain) {
//                            System.out.print("Do you want to Book the chair number again? (Y/N): ");
//                            bookngChair = sc.next();
//
//                            if (bookngChair.equalsIgnoreCase("Y")) {
//                                coditionInputChairNumberAgain = false;
//                                System.out.println("[+ ** ]The seat is updated *");
//                                tableArrBus[chairNumber - 1] = 1; // Mark the chair as booked
//                            } else if (bookngChair.equalsIgnoreCase("N")) {
//                                System.out.println("Thank you, Good Luck !!");
//                                coditionInputChairNumberAgain = false;
//                            } else {
//                                System.out.println(RED + "Wrong input. Please input only (Y/N)" + RESET);
//                            }
//                        }
//                    }
//                } else {
//                    System.out.println(RED + "Wrong input. Please input only [1 - " + tableNumber + "]" + RESET);
//                }
//            } else {
//                System.out.println(RED + "Invalid input. Please enter a valid integer." + RESET);
//            }
//        }
//
//    }
//}

import java.util.TreeSet;
import java.util.Iterator;
class Main {
    public static void main(String[] args) {
        TreeSet<String> alpha = new TreeSet<>();
        alpha.add("a");
        alpha.add("b");
        alpha.add("v");
        System.out.println("TreeSet: " + alpha);
        // Calling iterator() method
        Iterator<String> iterator = alpha.iterator();
        System.out.print("TreeSet using Iterator: ");
        // Accessing elements
        while(iterator.hasNext()) {
            System.out.print(iterator.next());
            System.out.print(", ");
        }
        System.out.println("meow");
        System.out.println("lazy cat");
        System.out.println("lazy cat too");


    }
}
