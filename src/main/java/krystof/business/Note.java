package krystof.business;

/**
 * Created by polansky on 8.12.2017.
 */
public class Note {
    private String name;

    public Note(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                '}';
    }
}
