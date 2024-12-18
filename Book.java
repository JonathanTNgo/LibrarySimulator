public class Book extends LibraryObject {
    private String author;
    private String publisher;
    
    public Book(String title, int id, String author, String publisher) {
        super(title, id);
        this.author = author;
        this.publisher = publisher;
    }
}
