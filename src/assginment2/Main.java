package assginment2;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
// Define menu option constants

public class Main {
    private static final String INSERT_EMPLOYEE = "1";
    private static final String DISPLAY_EMPLOYEES = "2";
    private static final String UPDATE_EMPLOYEE = "3";
    private static final String REMOVE_EMPLOYEE = "4";
    private static final String EXIT = "5";
    private static List<StaffMember> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mockData();
        while (true) {
//            System.out.println("\n--- Employee Management System ---");
//            System.out.println("1. Insert Employee");
//            System.out.println("2. Display Employee");
//            System.out.println("3. Update Employee Information (Except ID)");
//            System.out.println("4. Remove Employee");
//            System.out.println("0. Exit");
            displayMenuHome();

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    insertEmployee();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    updateEmployee();
                    break;
                case "4":
                    removeEmployee();
                    break;
                case "5":
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void displayMenuHome() {
        // Create a table with 2 columns
        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);

        // Define cell styles
        CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
        CellStyle leftAlign = new CellStyle(CellStyle.HorizontalAlign.left);

        // Add table headers
        table.addCell("Option", centerAlign);
        table.addCell("Description", leftAlign);

        // Add rows for each menu option
        table.addCell(INSERT_EMPLOYEE, centerAlign);
        table.addCell("Insert Employee", leftAlign);

        table.addCell(DISPLAY_EMPLOYEES, centerAlign);
        table.addCell("Display Employees", leftAlign);

        table.addCell(UPDATE_EMPLOYEE, centerAlign);
        table.addCell("Update Employee Information (Except ID)", leftAlign);

        table.addCell(REMOVE_EMPLOYEE, centerAlign);
        table.addCell("Remove Employee", leftAlign);

        table.addCell(EXIT, centerAlign);
        table.addCell("Exit", leftAlign);

        // Print the table
        System.out.println("\n--- Employee Management System ---");
        System.out.println(table.render());
    }

    private static void insertEmployee() {
        boolean retry = true;
        while (retry) {
            System.out.println("\n--- Insert Employee ---");
            System.out.print("Enter employee type (1. Volunteer, 2. Hourly Salary, 3. Salaried): ");
            int type;
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Do you want to try again? (Y/N): ");
                String choice = scanner.nextLine().trim().toUpperCase();
                if (!choice.equals("Y")) {
                    retry = false; // Exit the loop if the user chooses not to retry
                }
                continue;
            }

            // Validate name (only alphabetic characters and spaces allowed)
            String name;
            while (true) {
                System.out.print("Enter name: ");
                name = scanner.nextLine().trim();
                if (name.matches("[a-zA-Z ]+")) { // Only letters and spaces allowed
                    break;
                } else {
                    System.out.println("Invalid name. Only alphabetic characters and spaces are allowed.");
                }
            }

            System.out.print("Enter address: ");
            String address = scanner.nextLine();

            try {
                switch (type) {
                    case 1:
                        System.out.print("Enter salary: ");
                        double salary = Double.parseDouble(scanner.nextLine());
                        employees.add(new Volunteer(name, address, salary));
                        break;
                    case 2:
                        System.out.print("Enter hours worked: ");
                        int hoursWorked = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter rate per hour: ");
                        double rate = Double.parseDouble(scanner.nextLine());
                        employees.add(new HourlySalaryEmployee(name, address, hoursWorked, rate));
                        break;
                    case 3:
                        System.out.print("Enter base salary: ");
                        double baseSalary = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter bonus: ");
                        double bonus = Double.parseDouble(scanner.nextLine());
                        employees.add(new SalariedEmployee(name, address, baseSalary, bonus));
                        break;
                    default:
                        System.out.println("Invalid employee type.");
                        System.out.print("Do you want to try again? (Y/N): ");
                        String choice = scanner.nextLine().trim().toUpperCase();
                        if (!choice.equals("Y")) {
                            retry = false; // Exit the loop if the user chooses not to retry
                        }
                        continue;
                }
                System.out.println("Employee added successfully!");
                displayEmployees(); // Display the table after adding an employee
                retry = false; // Exit the loop after successful insertion
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Do you want to try again? (Y/N): ");
                String choice = scanner.nextLine().trim().toUpperCase();
                if (!choice.equals("Y")) {
                    retry = false; // Exit the loop if the user chooses not to retry
                }
            }
        }
    }
    private static void mockData() {
        employees.add(new SalariedEmployee("Alice", "123 Main St", 500.0,20 ));
        employees.add(new HourlySalaryEmployee("Bob", "456 Elm St", 40, 15.0));
        employees.add(new Volunteer("David", "321 Pine St",3390));
    }

    private static void displayEmployees() {
        System.out.println("\n--- Display Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            // Create a table with borders
            Table table = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Address");
            table.addCell("Type");
            table.addCell("Bonus");
            table.addCell("Rate");
            table.addCell("Hour");
            table.addCell("Salary");
            table.addCell("Pay");


            // Add employee data to the table
            employees.forEach(employee -> {
                table.addCell(String.valueOf(employee.getId())); // Add ID
                table.addCell(employee.getName());
                table.addCell(employee.getAddress());
                table.addCell(employee.getClass().getSimpleName());

                // Handle Bonus
                if (employee instanceof SalariedEmployee) {
                    table.addCell(String.format("%.2f$", ((SalariedEmployee) employee).getBonus()));
                } else {
                    table.addCell("--");
                }

                // Handle Rate
                if (employee instanceof HourlySalaryEmployee) {
                    table.addCell(String.format("%.2f$", ((HourlySalaryEmployee) employee).getRate()));
                } else {
                    table.addCell("--");
                }

                // Handle Hour
                if (employee instanceof HourlySalaryEmployee) {
                    table.addCell(String.valueOf(((HourlySalaryEmployee) employee).getHoursWorked()) + "h");
                } else {
                    table.addCell("--");
                }

                // Handle Salary
                if (employee instanceof Volunteer) {
                    table.addCell(String.format("%.2f$", ((Volunteer) employee).getSalary()));
                } else if (employee instanceof SalariedEmployee) {
                    table.addCell(String.format("%.2f$", ((SalariedEmployee) employee).getSalary()));
                } else if (employee instanceof HourlySalaryEmployee) {
                    double rate = ((HourlySalaryEmployee) employee).getRate();
                    int hoursWorked = ((HourlySalaryEmployee) employee).getHoursWorked();
                    double salary = rate * hoursWorked;
                    table.addCell(String.format(" %.2f$",  salary));
                } else {
                    double salaryFix = Math.random() * 10000;
                    table.addCell(String.format("%.2f$", salaryFix));
                }

                // Handle Pay
                if (employee instanceof SalariedEmployee) {
                    double pay = ((SalariedEmployee) employee).getSalary() + ((SalariedEmployee) employee).getBonus();
                    table.addCell(String.format("%.2f$", pay));
                } else {
                    table.addCell(String.format("%.2f$", employee.pay()));
                }
            });

            // Render the table
            System.out.println(table.render());
        }
    }
    private static void updateEmployee() {
        boolean retry = true;
        while (retry) {
            System.out.println("\n--- Update Employee Information ---");
            System.out.print("Enter employee ID: ");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            // Using Java 8 Stream and Optional to find the employee
            Optional<StaffMember> employeeOpt = employees.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst();

            if (employeeOpt.isPresent()) {
                StaffMember employee = employeeOpt.get();
                System.out.print("Enter new name (leave blank to keep current): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    employee.setName(name);
                }

                System.out.print("Enter new address (leave blank to keep current): ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) {
                    employee.setAddress(address);
                }

                // Update specific fields based on employee type
                if (employee instanceof HourlySalaryEmployee) {
                    HourlySalaryEmployee hourlyEmployee = (HourlySalaryEmployee) employee;

                    System.out.print("Enter new rate (leave blank to keep current): ");
                    String rateInput = scanner.nextLine();
                    if (!rateInput.isEmpty()) {
                        try {
                            double rate = Double.parseDouble(rateInput);
                            hourlyEmployee.setRate(rate);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Rate must be a number.");
                        }
                    }

                    System.out.print("Enter new hours worked (leave blank to keep current): ");
                    String hoursInput = scanner.nextLine();
                    if (!hoursInput.isEmpty()) {
                        try {
                            int hoursWorked = Integer.parseInt(hoursInput);
                            hourlyEmployee.setHoursWorked(hoursWorked);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Hours worked must be a number.");
                        }
                    }
                } else if (employee instanceof SalariedEmployee) {
                    SalariedEmployee salariedEmployee = (SalariedEmployee) employee;

                    System.out.print("Enter new salary (leave blank to keep current): ");
                    String salaryInput = scanner.nextLine();
                    if (!salaryInput.isEmpty()) {
                        try {
                            double salary = Double.parseDouble(salaryInput);
                            salariedEmployee.setSalary(salary);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Salary must be a number.");
                        }
                    }

                    System.out.print("Enter new bonus (leave blank to keep current): ");
                    String bonusInput = scanner.nextLine();
                    if (!bonusInput.isEmpty()) {
                        try {
                            double bonus = Double.parseDouble(bonusInput);
                            salariedEmployee.setBonus(bonus);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Bonus must be a number.");
                        }
                    }
                } else if (employee instanceof Volunteer) {
                    Volunteer volunteer = (Volunteer) employee;

                    System.out.print("Enter new salary (leave blank to keep current): ");
                    String salaryInput = scanner.nextLine();
                    if (!salaryInput.isEmpty()) {
                        try {
                            double salary = Double.parseDouble(salaryInput);
                            volunteer.setSalary(salary);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Salary must be a number.");
                        }
                    }
                }

                System.out.println("Employee information updated successfully!");
                displayEmployees(); // Display the table after updating an employee
                retry = false; // Exit the loop after successful update
            } else {
                System.out.println("Employee not found.");
                System.out.print("Do you want to try again? (Y/N): ");
                String choice = scanner.nextLine().trim().toUpperCase();
                if (!choice.equals("Y")) {
                    retry = false; // Exit the loop if the user chooses not to retry
                }
            }
        }
    }

    private static void removeEmployee() {
        boolean retry = true;
        while (retry) {
            System.out.println("\n--- Remove Employee ---");
            System.out.print("Enter employee ID: ");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            // Using Java 8 Stream to filter and collect remaining employees
            List<StaffMember> remainingEmployees = employees.stream()
                    .filter(e -> e.getId() != id)
                    .collect(Collectors.toList());

            if (remainingEmployees.size() < employees.size()) {
                employees = remainingEmployees;
                System.out.println("Employee removed successfully!");
                displayEmployees(); // Display the table after removing an employee
                retry = false; // Exit the loop after successful removal
            } else {
                System.out.println("Employee not found.");
                System.out.print("Do you want to try again? (Y/N): ");
                String choice = scanner.nextLine().trim().toUpperCase();
                if (!choice.equals("Y")) {
                    retry = false; // Exit the loop if the user chooses not to retry
                }
            }
        }
    }
}