package assginment2;

public class HourlySalaryEmployee  extends  StaffMember{
    private int hourWorked;
    private double rate;

    public HourlySalaryEmployee(int id, String name, String address,int hourWorked, double rate) {
        super(id, name, address);
        this.hourWorked = hourWorked;
        this.rate = rate;
    }

    public double pay(){
        System.out.println("Pay in HourlySalaryEmployee");
        return 1;
    }

    @Override
    public String toString() {
        return "HourlySalaryEmployee{" +
                "hourWorked=" + hourWorked +
                ", rate=" + rate +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
