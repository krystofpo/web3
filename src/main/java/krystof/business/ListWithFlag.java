package krystof.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by polansky on 26.12.2017.
 */
public class ListWithFlag {
    private boolean containsNonExistingLabel =false;
    private List<Label> existingLabels = new ArrayList<>();

    public ListWithFlag() {
    }


    public void setContainsNonExistingLabel(boolean containsNonExistingLabel) {
        this.containsNonExistingLabel = containsNonExistingLabel;
    }

    public List<Label> getExistingLabels() {
        return existingLabels;
    }

    public void setExistingLabels(List<Label> existingLabels) {
        this.existingLabels = existingLabels;
    }

    public boolean containsNonExistingLabel() {
        return containsNonExistingLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListWithFlag)) return false;

        ListWithFlag that = (ListWithFlag) o;

        if (containsNonExistingLabel != that.containsNonExistingLabel) return false;
        return getExistingLabels() != null ? getExistingLabels().equals(that.getExistingLabels()) : that.getExistingLabels() == null;
    }

    @Override
    public int hashCode() {
        int result = (containsNonExistingLabel ? 1 : 0);
        result = 31 * result + (getExistingLabels() != null ? getExistingLabels().hashCode() : 0);
        return result;
    }
}
