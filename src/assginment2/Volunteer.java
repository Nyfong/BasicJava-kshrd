package assginment2;

public class Volunteer extends StaffMember {
    private double salary;

    public Volunteer(String name, String address, double salary) {
        super(name, address); // Auto-increment id is handled in the parent class
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double pay() {
        return salary; // Assuming salary is a fixed amount for volunteers
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}