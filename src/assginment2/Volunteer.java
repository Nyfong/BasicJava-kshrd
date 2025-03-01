package assginment2;

public class Volunteer extends StaffMember {
    private double salary;
    public Volunteer( int id, String name, String address, double salary ){
        super( id,  name, address);
        this.salary = salary;
    }
    @Override
    public double pay(){
        System.out.println("Pay in volunteer");
        return 1;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
