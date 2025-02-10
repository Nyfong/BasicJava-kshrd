package homework002;

public class CheckingAccount implements Account {
    private String accountNumber;
    private String userName;
    private String dateOfBirth;
    private char gender;
    private String phoneNumber;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Deposit method
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        double newBalance = getBalance() + amount; // Add deposit to current balance
        setBalance(newBalance); // Update the balance
        System.out.println("New balance after deposit: " + newBalance); // Display updated balance
    }

    // Withdraw method
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > getBalance()) {
            System.out.println("Insufficient funds. Cannot withdraw " + amount + "$.");
            return;
        }
        double newBalance = getBalance() - amount; // Subtract withdrawal from current balance
        setBalance(newBalance); // Update the balance
        System.out.println("New balance after withdrawal: " + newBalance); // Display updated balance
    }

    // Transfer method
    @Override
    public void transfer(double amount, Account targetAccount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (amount > getBalance()) {
            System.out.println("Insufficient funds. Cannot transfer " + amount + "$.");
            return;
        }
        // Withdraw from this account
        withdraw(amount);
        // Deposit into the target account
        targetAccount.deposit(amount);
        System.out.println("Transfer of " + amount + "$ to account " + ((CheckingAccount) targetAccount).getAccountNumber() + " completed.");
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> Checking Account <<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Account Type: Checking Account");
        System.out.println("Account Number: " + (this.accountNumber == null ? "not yet created an account" : this.accountNumber));
        System.out.println("User Name: " + (this.userName == null ? "not yet created an account" : this.userName));
        System.out.println("Date of Birth: " + (this.dateOfBirth == null ? "not yet created an account" : this.dateOfBirth));
        System.out.println("Gender: " + (this.gender == ' ' ? "not yet created an account" : this.gender));
        System.out.println("Phone Number:" + (this.phoneNumber == null ? "not yet created an account" : this.phoneNumber));
        System.out.println("Balance: " + getBalance() + "$");
    }
}