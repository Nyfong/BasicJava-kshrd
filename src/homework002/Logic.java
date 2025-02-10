package homework002;

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
        this.checkingAccount = new CheckingAccount(); // Initialize
        this.savingAccount = new SavingAccount();
        this.sc = new Scanner(System.in);
    }

    // Case 1: Create Account
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
            System.out.println(RED + "The account is already created" + RESET);
        }
    }

    // Helper method to choose account type
    private void choseTheSavingOrCheckingAccount(int typeOfOps) {
        System.out.println(GREEN + ">>>>>>>>>>>>>>>>>>>> " + RESET + typeOfMthodCase[typeOfOps] + GREEN + " <<<<<<<<<<<<<<<<<<<<" + RESET);
        System.out.println((typeOfOps == 3)?("1. Checking Account ->  Saving Account"):("1. Checking Account"));
        System.out.println((typeOfOps == 3)?("2. Saving Account ->  Checking Account "):("2. Saving Account"));
        System.out.println("3. Back");
        System.out.println("========================================");
    }

    // Case 1: Display options for creating an account
    private void displayOptionInCaseforCreate() {
        choseTheSavingOrCheckingAccount(0);
        System.out.println("What type of account do you want to create?");
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        if (input.matches("^[1-3]$")) {
            option = Integer.parseInt(input);
            if (option == 1) {
                createAccount("Checking account");
            } else if (option == 2) {
                createAccount("Saving account");
            } else {
                System.out.println("Thank you so much for using it.");
            }
        } else {
            System.out.println(RED + "Invalid input! Only digits are allowed (1-3)." + RESET);
            condition = false;
        }
    }

    // Case 2: Deposit money into an account
    private void depositMoney() {
        choseTheSavingOrCheckingAccount(1);
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        if (input.matches("^[1-3]$")) {
            option = Integer.parseInt(input);
            if (option == 1) {
                if (checkingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create an account first." + RESET);
                } else {
                    logicDepositNoneRate();
                }
            } else if (option == 2) {
                if (savingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create an account first." + RESET);
                } else {
                    logicDepositWithRate();
                }
            } else {
                System.out.println("Thank you so much for using it.");
            }
        } else {
            System.out.println(RED + "Invalid input! Only digits are allowed (1-3)." + RESET);
            condition = false;
        }
    }

    // Deposit logic for Checking Account
    private void logicDepositNoneRate() {
        System.out.print("Enter the money to deposit: ");
        input = sc.nextLine();
        if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
            checkingAccount.deposit(Integer.parseInt(input));
            System.out.println("==============================================================");
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid input! Please enter a positive number greater than 0.");
        }
    }

    // Deposit logic for Saving Account
    private void logicDepositWithRate() {
        System.out.print("Enter the money to deposit: ");
        input = sc.nextLine();
        if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
            savingAccount.deposit(Integer.parseInt(input));
            System.out.println("==============================================================");
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid input! Please enter a positive number greater than 0.");
        }
    }

    // Case 3: Withdraw money from an account
    private void withDrawMoney() {
        choseTheSavingOrCheckingAccount(2);
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        if (input.matches("^[1-3]$")) {
            option = Integer.parseInt(input);
            if (option == 1) {
                if (checkingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create an account first." + RESET);
                } else {
                    withdrawMoneyWithoutRate();
                }
            } else if (option == 2) {
                if (savingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create an account first." + RESET);
                } else {
                    withdrawMoneyWithRate();
                }
            } else {
                System.out.println("Thank you so much for using it.");
            }
        } else {
            System.out.println(RED + "Invalid input! Only digits are allowed (1-3)." + RESET);
            condition = false;
        }
    }

    // Withdraw logic for Checking Account
    private void withdrawMoneyWithoutRate() {
        System.out.print("Enter the money to withdraw: ");
        input = sc.nextLine();
        if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
            checkingAccount.withdraw(Integer.parseInt(input));
            System.out.println("==============================================================");
        } else {
            System.out.println("Invalid input! Please enter a positive number greater than 0.");
        }
    }

    // Withdraw logic for Saving Account
    private void withdrawMoneyWithRate() {
        System.out.print("Enter the money to withdraw: ");
        input = sc.nextLine();
        if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
            savingAccount.withdraw(Integer.parseInt(input));
            System.out.println("==============================================================");

        } else {
            System.out.println("Invalid input! Please enter a positive number greater than 0.");
        }
    }

    // Case 4: Transfer money between accounts
    // Case 4: Transfer money between accounts
    private void transfer() {
        choseTheSavingOrCheckingAccount(3);
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        if (input.matches("^[1-3]$")) {
            option = Integer.parseInt(input);
            if (option == 1) {
                // Transfer from Checking to Saving
                if (checkingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create a Checking Account first." + RESET);
                } else if (savingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create a Saving Account first." + RESET);
                } else {
                    System.out.print("Enter the amount to transfer: ");
                    input = sc.nextLine();
                    if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
                        int amount = Integer.parseInt(input);
                        checkingAccount.transfer(amount, savingAccount);
                    } else {
                        System.out.println("Invalid input! Please enter a positive number greater than 0.");
                    }
                }
            } else if (option == 2) {
                // Transfer from Saving to Checking
                if (savingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create a Saving Account first." + RESET);
                } else if (checkingAccount.getUserName() == null) {
                    System.out.println(RED + "Please create a Checking Account first." + RESET);
                } else {
                    System.out.print("Enter the amount to transfer: ");
                    input = sc.nextLine();
                    if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
                        int amount = Integer.parseInt(input);
                        savingAccount.transfer(amount, checkingAccount);
                    } else {
                        System.out.println("Invalid input! Please enter a positive number greater than 0.");
                    }
                }
            } else {
                System.out.println("Thank you so much for using it.");
            }
        } else {
            System.out.println(RED + "Invalid input! Only digits are allowed (1-3)." + RESET);
            condition = false;
        }
    }


    // Case 5: Display account information
    private void displayAccountCase5() {
        if (checkingAccount.getAccountNumber() == null && savingAccount.getAccountNumber() == null) {
            System.out.println(RED + "========= Please create an account first =========" + RESET);
        } else {
            // Display checking account info if it exists
            if (checkingAccount.getAccountNumber() != null) {
               // System.out.println("========= Checking Account Information =========");
                checkingAccount.displayAccountInfo();
            } else {
                System.out.println(RED + "No Checking Account found." + RESET);
            }

            // Display saving account info if it exists
            if (savingAccount.getAccountNumber() != null) {
                //System.out.println("========= Saving Account Information =========");
                savingAccount.displayAccountInfo();
            } else {
                System.out.println(RED + "No Saving Account found." + RESET);
            }

            // Optional: Add a closing message or additional information
            System.out.println("Thank you so much for using it.");
        }
    }


    private void deleteAccount() {
        choseTheSavingOrCheckingAccount(5);
        System.out.print("Enter your choice: ");
        input = sc.nextLine();
        if (input.matches("^[1-3]$")) {
            option = Integer.parseInt(input);
            if (option == 1) {
                if (checkingAccount.getUserName() == null) {
                    System.out.println(RED + "No Checking Account exists to delete." + RESET);
                } else {
                    checkingAccount = new CheckingAccount();
                    System.out.println("==============================================================");
                    System.out.println("Checking Account has been deleted successfully!");
                }
            } else if (option == 2) {
                if (savingAccount.getUserName() == null) {
                    System.out.println(RED + "No Saving Account exists to delete." + RESET);
                } else {
                    savingAccount = new SavingAccount();
                    System.out.println("==============================================================");
                    System.out.println("Saving Account has been deleted successfully!");
                }
            } else {
                System.out.println("Thank you so much for using it.");
            }
        } else {
            System.out.println(RED + "Invalid input! Only digits are allowed (1-3)." + RESET);
            condition = false;
        }
    }

    // Main menu
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
                    case 1 -> displayOptionInCaseforCreate();
                    case 2 -> depositMoney();
                    case 3 -> withDrawMoney();
                    case 4 -> transfer();
                    case 5 -> displayAccountCase5();
                    case 6->{
                        deleteAccount();
                    }
                    case 7 -> {
                        System.out.println("Thank you for using this system.");
                        System.exit(0);
                    }
                    default -> System.out.println("Please enter only 1 to 7.");
                }
                condition = false;
            } else {
                System.out.println(RED + "Invalid input! Only digits are allowed (1-7)." + RESET);
                condition = false;
            }
        } while (!condition);
    }
}