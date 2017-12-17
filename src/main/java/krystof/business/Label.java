package krystof.business;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_id")
    private Long labelId;

    private String label;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "labels")
    private Set<Note> notes;

    protected Label() {
    }

    public Label(String label) {
        this.label = label;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label1 = (Label) o;
        return Objects.equals(getLabel(), label1.getLabel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLabel());
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", label='" + label + '\'' +
                '}';
    }
}
