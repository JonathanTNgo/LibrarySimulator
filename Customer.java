public class Customer {
    private static int nextCustomerID = 1;

    private String name;
    private int id;

    public Customer(String name) {
        this.name = name;
        id = nextCustomerID++;
    }

    public String get_name() {
        return name;
    }

    public int get_id() {
        return id;
    }

    @Override
    public String toString() {
        return name + " ID: " + id;
    }
}
