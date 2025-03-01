package assginment2;



public abstract class StaffMember {
    private static int nextId = 1; // Static variable to track the next ID

    protected int id;
    protected String name;
    protected String address;

    public StaffMember(String name, String address) {
        this.id = nextId++; // Assign the next available ID and increment
        this.name = name;
        this.address = address;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    public abstract double pay();

    @Override
    public String toString() {
        return "StaffMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}