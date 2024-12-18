public class Book extends LibraryObject {
    private String author;
    private String publisher;
    
    public Book(String title, int id, String author, String publisher) {
        super(title, id);
        this.author = author;
        this.publisher = publisher;
    }

    public Book(String title, int id, String author, String publisher, int total_copies) {
        super(title, id, total_copies);
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
        return super.get_title() + " by " + author + ". Published by " + publisher + ".";
    }
}
