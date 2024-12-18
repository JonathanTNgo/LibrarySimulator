public abstract class LibraryObject {
    private String title;
    private int id;
    private int total_copies;
    private int avaliable_copies;

    public LibraryObject(String title, int id) {
        this.title = title;
        this.id = id;
        total_copies = avaliable_copies = 1;
    }


    public LibraryObject(String title, int id, int avaliable_copies) {
        this.title = title;
        this.id = id;
        this.avaliable_copies = total_copies = avaliable_copies;
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
    public String toString() {
        return title + ", " + id;
    }
}
