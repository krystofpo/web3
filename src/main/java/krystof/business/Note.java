package krystof.business;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Long noteId;

    private String note;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "Note_Label",
            joinColumns = @JoinColumn(
                    name = "NoteId",
                    referencedColumnName = "note_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "LabelId",
                    referencedColumnName = "label_id"))
    private Set<Label> labels;

    //kvuli JPA defaultni konsturkto
    protected Note() {
    }

    public Note(String note, Set<Label> labels) {
        this.note = note;
        this.labels = labels;
    }

    public Note(String note) {
        this(note, null);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Long getNoteId() {
        return noteId;
    }

    //asi kvuli JPA? nevim ozkouset i bez toho
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                ", labels='" + labels + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note1 = (Note) o;

        if (getNote() != null ? !getNote().equals(note1.getNote()) : note1.getNote() != null) return false;
        return getLabels() != null ? getLabels().equals(note1.getLabels()) : note1.getLabels() == null;
    }

    @Override
    public int hashCode() {
        int result = getNote() != null ? getNote().hashCode() : 0;
        result = 31 * result + (getLabels() != null ? getLabels().hashCode() : 0);
        return result;
    }
}
