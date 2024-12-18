public abstract class LibraryObject {
    private static int nextObjectID = 1;

    private String title;
    private int id;
    private int total_copies;
    private int avaliable_copies;
    private LibraryType type;

    public LibraryObject(String title, LibraryType type) {
        this.title = title;
        this.type = type;
        id = nextObjectID++;
        total_copies = avaliable_copies = 1;
    }


    public LibraryObject(String title, int total_copies, LibraryType type) {
        this.title = title;
        this.type = type;
        id = nextObjectID++;
        this.total_copies = avaliable_copies = total_copies;
    }
    
    
    public ActionStatus check_out(int out) {
        if (avaliable_copies < out) {
            return ActionStatus.INSUFFICENT_COPIES;
        }

        avaliable_copies -= out;
        return ActionStatus.SUCCESS;
    }
    

    public ActionStatus check_out() {
        return check_out(1);
    }

    public ActionStatus remove(int out) {
        if (avaliable_copies < out) {
            return ActionStatus.INSUFFICENT_COPIES;
        } else {
            avaliable_copies -= out;
            total_copies -= out;
            return ActionStatus.SUCCESS;
        }
    }

    public LibraryType return_type() {
        return type;
    }

    public String get_title() {
        return title;
    }

    public int get_id() {
        return id;
    }

    public int get_total_copies() {
        return total_copies;
    }

    public int get_avaliable_copies() {
        return avaliable_copies;
    }

    @Override
    public abstract String toString();
}
