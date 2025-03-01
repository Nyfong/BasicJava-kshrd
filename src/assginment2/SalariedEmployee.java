package assginment2;

public class SalariedEmployee extends  StaffMember {
    private double bonus;
    private double salary;

    public SalariedEmployee(int id, String name, String address, double salary, double bonus) {
        super(id, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double pay(){
        System.out.println("Pay in SalariedEmployee");
        return 1;
    }

    @Override
    public String toString() {
        return "HourlySalaryEmployee{" +
                "bonus=" + bonus +
                ", salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
