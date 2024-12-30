import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LibrarySystem {
    // Map used because LibraryObjects will be searched via their unique IDs
        Map<Integer, LibraryObject> libraryByID = new HashMap<>();
        Map<String, List<LibraryObject>> libraryByName = new HashMap<>();
        // Map used because customers will be searched via their names
        Map<String, Customer> customersByName = new HashMap<>();
        // Map used because loans will be searched by customer names (the loanee)
        Map<String, List<Loan>> loans = new HashMap<>();

        // Methods for interacting with Library (inventory)

        // -- Future improvement: LibraryObjectFactory for better scalability
        public void addBook(String title, String author, String publisher, int total_copies) {
            if (total_copies <= 0) {
                return;
            }

            List<LibraryObject> toSearch = libraryByName.get(title);

            // Object doesn't exist by name, brand new object
            if (toSearch == null) {
                Book book = new Book(title, author, publisher, total_copies);
                libraryByID.put(book.get_id(), book);
                // Creating new (key, value) in map.
                ArrayList<LibraryObject> temp = new ArrayList<>();
                temp.add(book);
                libraryByName.put(title, temp);
            } else {
                // Object does exist by name, iterate and check if author/publisher matches
                for (int i = 0; i < toSearch.size(); i++) {
                    LibraryObject currObject = toSearch.get(i);
                    
                    if (currObject.get_type() == LibraryType.BOOK) {
                        Book book = (Book) currObject;

                        // Same book found, increment copies and DONE
                        if (book.get_author() == author && book.get_publisher() == publisher) {
                            book.add_copies(total_copies);
                            return;
                        }
                    }
                }

                // Book is unique but title is shared with others
                Book book = new Book(title, author, publisher, total_copies);
                libraryByID.put(book.get_id(), book);
                toSearch.add(book);
            }
        }

        public void addFilm(String title, String director, int release_year, int total_copies) {
            if (total_copies <= 0) {
                return;
            }
            
            List<LibraryObject> toSearch = libraryByName.get(title);

            // Object doesn't exist by name, brand new object
            if (toSearch == null) {
                Film film = new Film(title, director, release_year, total_copies);
                libraryByID.put(film.get_id(), film);
                // Creating new (key, value) in map.
                ArrayList<LibraryObject> temp = new ArrayList<>();
                temp.add(film);
                libraryByName.put(title, temp);
            } else {
                // Object does exist by name, iterate and check if author/publisher matches
                for (int i = 0; i < toSearch.size(); i++) {
                    LibraryObject currObject = toSearch.get(i);
                    
                    if (currObject.get_type() == LibraryType.FILM) {
                        Film film = (Film) currObject;

                        // Same book found, increment copies and DONE
                        if (film.get_director() == director && film.get_release_year() == release_year) {
                            film.add_copies(total_copies);
                            return;
                        }
                    }
                }

                // Book is new, add book to library
                Film film = new Film(title, director, release_year, total_copies);
                libraryByID.put(film.get_id(), film);
                toSearch.add(film);
            }
        }

        public void viewObjectByID(int id) {
            Optional<LibraryObject> result = getObjectByID(id);
            if (result.isPresent()) System.out.println(result.get());
        }
        
        public Optional<LibraryObject> getObjectByID(int id) {
            return Optional.ofNullable(libraryByID.get(id));
        }

        public Optional<List<LibraryObject>> getObjectByName(String name) {
            return Optional.ofNullable(libraryByName.get(name));
        }

        public void deleteSomeObjectByID(int id, int count) {
            // Deletes entire object if we are trying to delete MORE than the objects avaliable copies
            // Delete entire object if we are trying to delete more than total copies AND avaliable copies == total copies
            LibraryObject curr = libraryByID.get(id);
            if (curr.get_total_copies() == curr.get_avaliable_copies() && count >= curr.get_total_copies()) {
                deleteObjectByID(id);
                return;
            }

            // Try to remove (count) copies from Object total count.
            // If count > object.avaliable_copies, do NOT remove any copies, error to user.
            // If count < object.avaliable_copies, .remove will decrement object's total and avaliable copies by (count)
            if (libraryByID.get(id).remove(count) == ActionStatus.INSUFFICENT_COPIES) {
                System.out.println("Error: You are trying to delete more copies than avaliable. Copies are being loaned out still.");
                return;
            }
        }

        public void deleteObjectByID(int id) {
            if (libraryByID.get(id) == null) {
                System.out.println("Item does not exist.");
            } else {
                LibraryObject toDelete = libraryByID.get(id);
                libraryByID.remove(id);

                // Since object exists, delete it from libraryByName
                List<LibraryObject> toSearch = libraryByName.get(toDelete.get_title());
                for (int i = 0; i < toSearch.size(); i++) {
                    if (toSearch.get(i).get_id() == id) {
                        toSearch.remove(i);
                        return;
                    }
                }
                
                System.out.println("This message shouldn't be printed out (deleteObjectByID)");
            }
        } 

        public void viewLibrary() {
            libraryByID.forEach((id, object) -> System.out.println(object));
        }

        public void viewLibraryByName() {
            libraryByName.forEach((name, object) -> System.out.println(object));
        }


        // Methods for interacting with Customers
        // View all Customers
        public void viewAllCustomers() {
            customersByName.forEach((id, object) -> System.out.println(object));
        }

        // View singular customer by name
        public void viewCustomer(String name) {
            if (customersByName.get(name) == null) {
                System.out.println("No customer of name, " + name + ", exists.");
            } else {
                System.out.println(customersByName.get(name));
            }
        }

        // Create new customer
        public ActionStatus addCustomer(String name) {
            if (customersByName.get(name) == null) {
                customersByName.put(name, new Customer(name));
                return ActionStatus.SUCCESS;
            } else {
                return ActionStatus.NOT_AVAILABLE;
            }
        }

        // Delete customer if they do not have any outstanding loans
        public ActionStatus deleteCustomer(String name) {
            Customer curr = customersByName.get(name);
            if (curr == null) {
                return ActionStatus.NOT_AVAILABLE;
            } else {
                // Customer exists, only delete if that customer has no loans
                if (loans.get(curr.get_name()) == null) {
                    customersByName.remove(name);
                    return ActionStatus.SUCCESS;
                } else {
                    System.out.println("Customer cannot be deleted, they have an outstanding loan.");
                    return ActionStatus.NOT_AVAILABLE;
                }
            }
        }

        // Check if a customer exists
        public boolean doesCustomerExist(String name) {
            return (customersByName.get(name) != null);
        }
        

        // Methods for interacting with Loans
        // View all loans
        public void viewAllLoans() {
            loans.forEach((id, object) -> System.out.println(object));
        }

        // View loan(s) for 1 customer
        public void viewLoan(String customerName) {
            List<Loan> curr = loans.get(customerName);
            if (curr == null) {
                System.out.println(customerName + " has not outstanding loans.");
                return;
            }

            // print each loans in list
            curr.forEach((object) -> System.out.println(object));
        }

        // Make new loan
        public ActionStatus addLoan(String customerName, int libraryObjectID) {
            // Check if customer exists
            if (!doesCustomerExist(customerName)) {
                System.out.println("Customer of name: " + customerName + " does not exist.");
                return ActionStatus.NOT_AVAILABLE;
            }

            // Check if library object exists
            LibraryObject curr_object = libraryByID.get(libraryObjectID);
            if (curr_object == null) {
                System.out.println("Library object with id: " + libraryObjectID + " does not exist.");
                return ActionStatus.NOT_AVAILABLE;
            }

            // Check if object has enough copies
            if (curr_object.check_out(1) == ActionStatus.INSUFFICENT_COPIES) {
                System.out.println("Insufficent copies to checkout object of ID: " + libraryObjectID + ".");
                return ActionStatus.NOT_AVAILABLE;
            }

            // Check if they have any outstanding loans
            List<Loan> curr = loans.get(customerName);
            if (curr == null) {
                ArrayList<Loan> temp = new ArrayList<>();
                temp.add(new Loan(customerName, libraryObjectID));
                loans.put(customerName, temp);
            } else {
                curr.add(new Loan (customerName, libraryObjectID));
            }

            return ActionStatus.SUCCESS;
        }

        // Delete a loan
        public ActionStatus deleteLoan(String customerName, int loanID) {
            List<Loan> curr = loans.get(customerName);
            if (curr == null) {
                System.out.println("Customer has no outstanding loands");
                return ActionStatus.NOT_AVAILABLE;
            }

            // Find loanID and remove it
            for (int i = 0; i < curr.size(); i++) {
                if (curr.get(i).get_loan_id() == loanID) {
                    curr.remove(i);
                    System.out.println("Loan removed.");
                    return ActionStatus.SUCCESS;
                }
            }

            System.out.println("Loan does not exist.");
            return ActionStatus.NOT_AVAILABLE;
        }
}
