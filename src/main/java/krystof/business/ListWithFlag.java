package krystof.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by polansky on 26.12.2017.
 */
public class ListWithFlag {
    private boolean containsNonSavedLabel=false;
    private List<Label> savedLabels = new ArrayList<>();

    public ListWithFlag() {
    }


    public void setContainsNonSavedLabel(boolean containsNonSavedLabel) {
        this.containsNonSavedLabel = containsNonSavedLabel;
    }

    public List<Label> getSavedLabels() {
        return savedLabels;
    }

    public void setSavedLabels(List<Label> savedLabels) {
        this.savedLabels = savedLabels;
    }

    public boolean containsNonSavedLabel() {
        return containsNonSavedLabel;
    }
}
