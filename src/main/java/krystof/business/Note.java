package krystof.business;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Note {



    private String note;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;

    //kvuli JPA defaultni konsturkto
    protected Note() {
    }

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

    //asi kvuli JPA? nevim ozkouset i bez toho
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
