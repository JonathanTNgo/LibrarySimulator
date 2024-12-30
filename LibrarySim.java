import java.util.InputMismatchException;
import java.util.Scanner;

public class LibrarySim {
    public static void main (String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Virtual Library Simulator!");
        while (true) {
            System.out.println("\nWhat action would you like to perform?");
            System.out.println("1: Add a book");
            System.out.println("2: Add a film");
            System.out.println("3: View an object by ID");
            System.out.println("4: View the entire library (by ID)");
            System.out.println("5: View the entire library (by Name)");
            System.out.println("6: Delete object(s)");
            System.out.println("7: Add a customer");
            System.out.println("8: Delete a customer");
            System.out.println("9: View all customers");
            System.out.println("10: View a customer's outstanding loans");
            System.out.println("11: Add a loan");
            System.out.println("12: View all loans");
            System.out.println("13: Delete a loan");
            System.out.println("0: Exit");

            System.out.print("Enter your choice: ");
            int choice = -1;
            boolean is_valid = false;

            while (!is_valid) {
                try {
                    choice = scanner.nextInt();
                    is_valid = true;
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.print("Invalid input, please enter a number: ");
                }
            }
            
            scanner.nextLine();
            switch (choice) {
                // end
                case 0 -> {
                    return;
                }

                // Add book
                case 1 -> {
                    System.out.print("Enter the book's title: ");
                    String title = getString(scanner);

                    System.out.print("Enter the book's author: ");
                    String author = getString(scanner);

                    System.out.print("Enter the book's publisher: ");
                    String publisher = getString(scanner);

                    System.out.print("Enter the number of copies to add: ");
                    int copies = getInt(scanner);

                    library.addBook(title, author, publisher, copies);
                }
                 // Add film
                case 2 -> {
                    System.out.print("Enter the films's title: ");
                    String title = getString(scanner);

                    System.out.print("Enter the film's director: ");
                    String director = getString(scanner);

                    System.out.print("Enter the film's release year: ");
                    int year = getInt(scanner);

                    System.out.print("Enter the number of copies to add: ");
                    int copies = getInt(scanner);

                    library.addFilm(title, director, year, copies);
                }

                // View an object by ID
                case 3 -> {
                    System.out.print("Enter the ID of the object you are trying to view: ");
                    int id = getInt(scanner);

                    library.viewObjectByID(id);
                }

                // View entire library (by ID)
                case 4 -> {
                    library.viewLibrary();
                }
                
                // View entire library (by Name)
                case 5 -> {
                    library.viewLibraryByName();
                }

                case 6 -> {
                    System.out.print("Enter the ID of the object you are trying to delete: ");
                    int id = getInt(scanner);

                    System.out.print("Enter the number of copies you wish to remove: ");
                    int copies = getInt(scanner);

                    if (copies <= 0) {
                        break;
                    }

                    library.deleteSomeObjectByID(id, copies);
                }

                // Add customer
                case 7 -> {
                    System.out.print("Enter the name of the customer you want created: ");
                    String name = getString(scanner);

                    if (library.addCustomer(name) == ActionStatus.NOT_AVAILABLE) {
                        System.out.println("Customer name already exists.");
                    }
                }

                // Delete customer
                case 8 -> {
                    System.out.println("Warning: You won't be able to delete a customer with outstanding loans");
                    System.out.print("Enter the name of the customer you want deleted: ");
                    String name = getString(scanner);

                    if (library.deleteCustomer(name) == ActionStatus.NOT_AVAILABLE) {
                        System.out.println("Customer could not be succesfully deleted");
                    }
                }
                
                // View all customers
                case 9 -> {
                    library.viewAllCustomers();
                }

                // View a customer's outstanding loans
                case 10 -> {
                    System.out.print("Enter the name of the customer whose loans you want to view: ");
                    String name = getString(scanner);

                    library.viewLoan(name);
                }

                // Add a loan
                case 11 -> {
                    System.out.print("Enter the name of the loanee (customer name): ");
                    String name = getString(scanner);

                    System.out.print("Enter the Object ID of whats being loaned: ");
                    int id = getInt(scanner);

                    if (library.addLoan(name, id) == ActionStatus.NOT_AVAILABLE) System.out.println("Loan was not succesfully created.");
                }

                // View all Loans
                case 12 -> {
                    library.viewAllLoans();
                }

                // Delete a loan
                case 13 -> {
                    System.out.print("Enter the name of the customer whose loan you want deleted: ");
                    String name = getString(scanner);

                    System.out.print("Enter the ID of the loan you want deleted: ");
                    int id = getInt(scanner);

                    if (library.deleteLoan(name, id) == ActionStatus.NOT_AVAILABLE) System.out.println("Loan was not succesfully deleted.");
                }

                default -> {
                    System.out.println("Invalid choice, please try again");
                }
            }
        }

        
    }

    public static String getString(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.print("Please input a non-empty string: ");
            }
        }
    }

    public static int getInt(Scanner scanner) {
        boolean is_valid = false;
        int input = -1;

        while (!is_valid) {
            try {
                input = scanner.nextInt();
                is_valid = true;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Please input an integer: ");
            }
        }

        return input;
    }
}