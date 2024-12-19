public class Film extends LibraryObject {
    private String director;
    private int release_year;

    public Film(String title, String director, int release_year) {
        super(title, LibraryType.FILM);
        this.director = director;
        this.release_year = release_year;
    }

    public Film(String title, String director, int release_year, int total_copies) {
        super(title, total_copies, LibraryType.FILM);
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
        return super.get_title() + " (ID: " + super.get_id() + ") " + " (" + release_year + ")" + " by " + director + ". Avaliable copies: " + super.get_avaliable_copies() + " out of " + super.get_total_copies();
    }
}
