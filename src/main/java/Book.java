public class Book {
    private long id;
    private String title;
    private String authorFirstName;
    private String authorLastName;

    public Book() {
    }

    public Book(long id, String title, String authorLastName) {
        this.id = id;
        this.title = title;
        this.authorLastName = authorLastName;
    }

    public Book(long id, String title, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}
