package homework002;

import java.util.Scanner;

public class SavingAccount extends CheckingAccount implements Account {
    private String accountNumber;
    private String userName;
    private String dateOfBirth;
    private char gender;
    private String phoneNumber;
    private double balance = 0;
    private double saving = 0.05;
    Scanner sc = new Scanner(System.in);

    @Override
    public void displayAccountInfo() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> Saving Account <<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Account Type: Saving Account");
        System.out.println("Account Number: " + (this.accountNumber == null ? "not yet created an account" : this.accountNumber));
        System.out.println("User Name: " + (this.userName == null ? "not yet created an account" : this.userName));
        System.out.println("Date of Birth: " + (this.dateOfBirth == null ? "not yet created an account" : this.dateOfBirth));
        System.out.println("Gender: " + (this.gender == ' ' ? "not yet created an account" : this.gender));
        System.out.println("Phone Number:" + (this.phoneNumber == null ? "not yet created an account" : this.phoneNumber));
        System.out.println("Balance: " + getBalance() + "$");
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Insufficient funds for transfer.");
            return;
        }

        this.balance -= amount;
        targetAccount.deposit(amount);
        System.out.println("Transfer successful!");
        System.out.println("New balance: " + this.balance + "$");
        // Deposit into the target account
        targetAccount.deposit(amount);
        System.out.println("Transfer of " + amount + "$ to account " + ((CheckingAccount) targetAccount).getAccountNumber() + " completed.");
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        // Check if the withdrawal amount is within the 80% limit
        double maxWithdrawableAmount = this.balance * 0.80;
        if (amount > maxWithdrawableAmount) {
            System.out.println("You can only withdraw up to 80% of your balance. The maximum withdrawal amount is: " + maxWithdrawableAmount + "$");
            return;
        }

        if (amount > this.balance) {
            System.out.println("Insufficient funds for withdrawal.");
            return;
        }

        double fee = amount * saving;
        double totalWithdrawal = amount + fee;

        if (totalWithdrawal > this.balance) {
            System.out.println("Insufficient funds including withdrawal fee.");
            return;
        }

        this.balance -= totalWithdrawal;
        System.out.println("Withdrawal successful!");
        System.out.println("Amount withdrawn: " + amount + "$");
        System.out.println("Fee charged: " + fee + "$");

        System.out.println("New balance: " + this.balance + "$");
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        // Check if the deposit is greater than or equal to 200
        if (amount >= 200) {
            double bonus = amount * saving; // 5% of the deposit
            amount += bonus; // Add bonus to the deposit amount
            System.out.println("Bonus of " + bonus + "$ added to your deposit.");
        }

        setBalance(this.balance + amount);
        System.out.println("New balance after deposit: " + this.balance);
    }

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

    public double getSaving() {
        return saving;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }
}