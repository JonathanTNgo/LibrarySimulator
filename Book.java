public class Book extends LibraryObject {
    private static int nextBookID = 1;

    private String author;
    private String publisher;
    
    public Book(String title, String author, String publisher) {
        super(title, nextBookID++);
        this.author = author;
        this.publisher = publisher;
    }

    public Book(String title, String author, String publisher, int total_copies) {
        super(title, nextBookID++, total_copies);
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
