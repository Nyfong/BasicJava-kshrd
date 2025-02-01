package homework3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logic obj = new Logic();
        System.out.println(obj);
//        obj.setTitle("de");
//        obj.setPublishYear("");
//        obj.setStatus("a");
//        obj.setId(10);
//        obj.setAuthor(null);
        obj.userName = "KDET";
        obj.libraryAddress = "Battambang";
//       // obj.setUpLibrary();
        obj.displayMenu();

    }
}
