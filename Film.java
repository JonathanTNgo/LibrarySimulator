public class Film extends LibraryObject {
    private String director;
    private int release_year;

    public Film(String title, String director, int release_year) {
        super(title);
        this.director = director;
        this.release_year = release_year;
    }

    public Film(String title, String director, int release_year, int total_copies) {
        super(title, total_copies);
        this.director = director;
        this.release_year = release_year;
    }

    public String get_director() {
        return director;
    }

    public int get_release_year() {
        return release_year;
    }

    @Override
    public String toString() {
        return super.get_title() + " (" + release_year + ")" + " by " + director + ".";
    }
}
