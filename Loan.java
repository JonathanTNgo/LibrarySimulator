public class Loan {
    private static int nextLoanID = 1;

    private int loanID;
    private String customerName;
    private int objectID;

    public Loan(String customerName, int objectID) {
        loanID = nextLoanID++;
        this.customerName = customerName;
        this.objectID = objectID;
    }

    public int get_loan_id() {
        return loanID;
    }

    public String get_customer_name() {
        return customerName;
    }

    public int get_object_id() {
        return objectID;
    }

    @Override
    public String toString() {
        return "Loan: " + loanID + " of " + objectID + " from " + customerName + ".";
    }
}
