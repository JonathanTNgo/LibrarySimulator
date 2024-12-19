public class Book extends LibraryObject {
    private String author;
    private String publisher;
    
    public Book(String title, String author, String publisher) {
        super(title, LibraryType.BOOK);
        this.author = author;
        this.publisher = publisher;
    }

    public Book(String title, String author, String publisher, int total_copies) {
        super(title, total_copies, LibraryType.BOOK);
        this.author = author;
        this.publisher = publisher;
    }

    public String get_author() {
        return author;
    }

    public String get_publisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return super.get_title() + " (ID: " + super.get_id() + ") by " + author + ". Published by " + publisher + ". Avaliable copies: " + super.get_avaliable_copies() + " out of " + super.get_total_copies();
    }
}
