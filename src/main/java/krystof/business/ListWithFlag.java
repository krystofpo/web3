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
}
