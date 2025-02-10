package homework002;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Logic {

    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;
    public String input;
    public int option;
    public String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
    public boolean condition = true;
    Scanner sc = new Scanner(System.in);
    private String[] typeOfMthodCase = {"Create Account", "Deposit Money", "Withdraw Money", "Transfer Money", "Display Account Information", "Delete Account"};

    // Constructor to initialize objects
    public Logic() {
        this.checkingAccount = new CheckingAccount(); //  Initialize
        this.savingAccount = new SavingAccount();
        this.sc = new Scanner(System.in);
    }

    private void createAccount(String typeOfAcc) {

        if (typeOfAcc.equalsIgnoreCase("Checking account") ? checkingAccount.getUserName() == null : savingAccount.getUserName() == null) {
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + typeOfAcc + " Information <<<<<<<<<<<<<<<<<<<<");
            // Validate Username (only letters and spaces)
            String userName;
            while (true) {
                System.out.print("Enter username: ");
                userName = sc.nextLine();
                if (userName.matches("^[a-zA-Z ]+$")) {
                    break;
                } else {
                    System.out.println("Invalid input! Username must contain only letters and spaces.");
                }
            }

            // Validate Date of Birth (dd-mm-yyyy)
            String dateOfBirth;
            while (true) {
                System.out.print("Enter date of birth (dd-mm-yyyy): ");
                dateOfBirth = sc.nextLine();
                if (dateOfBirth.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
                    break;
                } else {
                    System.out.println("Invalid format! Please enter date in dd-mm-yyyy format.");
                }
            }

            // Validate Gender (M/F)
            char gender;
            while (true) {
                System.out.print("Enter gender (M/F): ");
                String genderInput = sc.nextLine().toUpperCase();
                if (genderInput.equals("M") || genderInput.equals("F")) {
                    gender = genderInput.charAt(0);
                    break;
                } else {
                    System.out.println("Invalid input! Enter 'M' for Male or 'F' for Female.");
                }
            }

            // Validate Phone Number (8-12 digits)
            String phoneNumber;
            while (true) {
                System.out.print("Enter phone number: ");
                phoneNumber = sc.nextLine();
                if (phoneNumber.matches("^[0-9]{8,12}$")) {  // Accepts 8 to 12 digits
                    break;
                } else {
                    System.out.println("Invalid phone number! It must contain only digits (8-12 characters).");
                }
            }

            // Account Number Generation
            String accountNumber = "ACC" + (int) (Math.random() * 1000000);
            if (typeOfAcc.equalsIgnoreCase("Saving account")) {
                savingAccount.setUserName(userName);
                savingAccount.setAccountNumber(accountNumber);
                savingAccount.setDateOfBirth(dateOfBirth);
                savingAccount.setGender(gender);
                savingAccount.setPhoneNumber(phoneNumber);

            } else if (typeOfAcc.equalsIgnoreCase("Checking account")) {
                checkingAccount.setUserName(userName);
                checkingAccount.setAccountNumber(accountNumber);
                checkingAccount.setDateOfBirth(dateOfBirth);
                checkingAccount.setGender(gender);
                checkingAccount.setPhoneNumber(phoneNumber);
            }
            // Display Success Message
            System.out.println("==============================================================");
            System.out.println("Your " + typeOfAcc + " has been created successfully!");
            System.out.println("Account Number: " + (typeOfAcc.equalsIgnoreCase("Saving account") ? savingAccount.getAccountNumber() : typeOfAcc.equalsIgnoreCase("Checking account") ? checkingAccount.getAccountNumber() : ""));
            System.out.println("Username: " + (typeOfAcc.equalsIgnoreCase("Saving account") ? savingAccount.getUserName() : typeOfAcc.equalsIgnoreCase("Checking account") ? checkingAccount.getUserName() : ""));
            System.out.println("Date of Birth: " + (typeOfAcc.equalsIgnoreCase("Saving account") ? savingAccount.getDateOfBirth() : typeOfAcc.equalsIgnoreCase("Checking account") ? checkingAccount.getDateOfBirth() : ""));
            System.out.println("Gender: " + (typeOfAcc.equalsIgnoreCase("Saving account") ? savingAccount.getGender() : typeOfAcc.equalsIgnoreCase("Checking account") ? checkingAccount.getGender() : ""));
            System.out.println("Phone Number: " + (typeOfAcc.equalsIgnoreCase("Saving account") ? savingAccount.getPhoneNumber() : typeOfAcc.equalsIgnoreCase("Checking account") ? checkingAccount.getPhoneNumber() : ""));
        } else {
            System.out.println(RED + "the account is already created" + RESET);
        }
    }

    private void choseTheSavingOrCheckingAccount(int typeOfOps) {
        System.out.println(GREEN + ">>>>>>>>>>>>>>>>>>>> " + RESET + typeOfMthodCase[typeOfOps] + GREEN + " <<<<<<<<<<<<<<<<<<<<" + RESET);
        System.out.println("1. Checking Account");
        System.out.println("2. Saving Account");
        System.out.println("3. Back");
        System.out.println("========================================");
    }

    //display
    private void displayOptionInCaseforCreate() {
        choseTheSavingOrCheckingAccount(0);
        System.out.println("What type of account do you want to create ?");
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        if (input.matches("^[1-3]$")) {
            option = Integer.parseInt(input);
            if (option == 1) {
                createAccount("Checking account");
            } else if (option == 2) {
                createAccount("Saving account");
            } else {
                System.out.println("Thank you so much for using it. ");
            }
        } else {
            System.out.println(RED + "Invalid Only degit are allowed. and 1 to 3" + RESET);
            condition = false;
        }


    }

    //case 5
    private void displayAccountCase5() {
        if (checkingAccount.getAccountNumber() == null && savingAccount.getAccountNumber() == null) {
            System.out.println("please create the account first");
        } else {
            choseTheSavingOrCheckingAccount(4);
            System.out.print("Enter your choice: ");
            input = sc.nextLine();
            if (input.matches("^[1-3]$")) {
                option = Integer.parseInt(input);
                if (option == 1) {
                    checkingAccount.displayAccountInfo();
                } else if (option == 2) {
                    savingAccount.displayAccountInfo();
                } else {
                    System.out.println("Thank you so much for using it. ");
                }
            } else {
                System.out.println(RED + "Invalid Only degit are allowed. and 1 to 3" + RESET);
                condition = false;
            }
        }
    }

    //main case
    public void displayMenu() {
        do {
            System.out.println("================== Online Banking System ==================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Display Account Information");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("=> Choose option (1-7): ");
            input = sc.nextLine();
            if (input.matches("^[1-7]\\d*$")) {
                option = Integer.parseInt(input);
                switch (option) {
                    case 1 -> {
                        displayOptionInCaseforCreate();
                        condition = false;
                    }
                    case 5 -> {
                        displayAccountCase5();
                        condition = false;
                    }

                    case 7 -> {
                        System.out.println("Thnak you for using this");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Please enter only 1 to 7");
                        condition = false;

                    }
                }

            } else {
                System.out.println(RED + "Invalid Only degit are allowed. and 1 to 7" + RESET);
                condition = false;
            }
        } while (!condition);


    }
}
