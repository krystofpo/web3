package krystof.business;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Long noteId;

    private String note;

    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "Note_Label",
            joinColumns = @JoinColumn(
                    name = "NoteId",
                    referencedColumnName = "note_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "LabelId",
                    referencedColumnName = "label_id"))
    private final List<Label> labels = new SetUniqueListI(new ArrayList<Label>(), new HashSet<Label>());


    protected Note() {
    }

    public Note(String note, List<Label> labels) {
        this.note = note;
        this.labels.addAll(labels);
    }

    public Note(Long noteId, String note, List<Label> labels) {
        this.noteId = noteId;
        this.note = note;
        this.labels.addAll(labels);
    }


    public Note(String note) {
        this(note, null);
    }

    public Note(Note note) {

        this(note.getNoteId(), note.getNote(), note.getLabels());

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

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels.clear();
        this.labels.addAll(labels);
    }


    public void correctBeforeSave() {
        ((SetUniqueListI)labels).correctList();
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", note='" + note + '\'' +
                ", labels=" + labels +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

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
