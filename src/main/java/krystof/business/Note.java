package krystof.business;

/**
 * Created by polansky on 8.12.2017.
 */
public class Note {
    private String note;
    private Long id;
    private String label;

    public Note(String note, String label) {
        this.note = note;
        this.label = label;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!getNote().equals(note.getNote())) return false;
        if (!getId().equals(note.getId())) return false;
        return getLabel().equals(note.getLabel());
    }

    @Override
    public int hashCode() {
        int result = getNote().hashCode();
        result = 31 * result + getLabel().hashCode();
        return result;
    }
}
