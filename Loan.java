public class Loan {
    private static int nextLoanID = 1;

    private int loanID;
    private int customerID;
    private int objectID;

    public Loan(int customerID, int objectID) {
        loanID = nextLoanID++;
        this.customerID = customerID;
        this.objectID = objectID;
    }

    public int get_loan_id() {
        return loanID;
    }

    public int get_customer_id() {
        return customerID;
    }

    public int get_object_id() {
        return objectID;
    }

    @Override
    public String toString() {
        return "Loan: " + loanID + " of " + objectID + " from " + customerID + ".";
    }
}
