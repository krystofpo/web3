package krystof.business;

/**
 * Created by polansky on 27.12.2017.
 */
public class LabelWithFlag {
    private boolean nonExisting = false;
    private Label existingLabel=null;

    public LabelWithFlag(Label existingLabel) {
        this.existingLabel = existingLabel;
    }

    public LabelWithFlag() {

    }

    public boolean isNonExisting() {
        return nonExisting;
    }

    public void setNonExisting(boolean nonExisting) {
        this.nonExisting = nonExisting;
    }

    public Label getExistingLabel() {
        return existingLabel;
    }

    public void setExistingLabel(Label existingLabel) {
        this.existingLabel = existingLabel;
    }
}
