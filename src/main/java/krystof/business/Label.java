package krystof.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Label implements Comparable<Label>, Validable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_id")
    private Long labelId;

    private String label;

    public Label() {
    }

    public Label(String label) {

        this.labelId = null;
        this.label = label;
    }

    public Label(Long labelId, String label) {

        this.labelId = labelId;
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Label)) return false;
//
//        Label label1 = (Label) o;
//
//        if (getLabel()==null && label1.getLabel()==null) {
//            return false;
//        }
//        return getLabel().equals(label1.getLabel());
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;

        Label label1 = (Label) o;

        return getLabel() != null ? getLabel().equals(label1.getLabel()) : label1.getLabel() == null;
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

    @Override
    public int compareTo(Label other) {
        return this.label.compareTo(other.getLabel());
    }

    @Override
    public boolean isValid() {
        if (label==null) {
            return false;
        }
        return !("".equals(label));
    }
}
