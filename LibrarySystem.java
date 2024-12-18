import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystem {
    // Map used because LibraryObjects will be searched via their unique IDs
        Map<Integer, LibraryObject> libraryByID = new HashMap<>();
        Map<String, List<LibraryObject>> libraryByName = new HashMap<>();
        // Map used because customers will be searched via their names
        // List is used in case of duplicate names (IDs still unique)
        Map<String, List<Customer>> customersByName = new HashMap<>();
        // Map used because loans will be searched by customer IDs (the loanee)
        Map<Integer, Loan> loans = new HashMap<>();
        

        // Methods for interacting with Library (inventory)

        // -- Future improvement: LibraryObjectFactory for better scalability
        public void addBook(String title, String author, String publisher, int total_copies) {
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

        public void viewLibrary() {
            libraryByID.forEach((id, object) -> System.out.println(object));
        }

        public void viewLibraryByName() {
            libraryByName.forEach((name, object) -> System.out.println(object));
        }



        // Methods for interacting with Customers




        // Methods for interacting with Loans
}
