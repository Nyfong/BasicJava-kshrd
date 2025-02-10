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

    @Override
    public void deposit(double amount) {

    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public void transfer(double amount, Account targetAccount) {

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
        System.out.println("Balance: " + this.balance + "$");
    }

}
