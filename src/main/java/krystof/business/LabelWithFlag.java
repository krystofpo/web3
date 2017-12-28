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

    public LabelWithFlag(boolean nonExisting, Label existingLabel) {
        this.nonExisting = nonExisting;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabelWithFlag)) return false;

        LabelWithFlag that = (LabelWithFlag) o;

        if (isNonExisting() != that.isNonExisting()) return false;
        return getExistingLabel() != null ? getExistingLabel().equals(that.getExistingLabel()) : that.getExistingLabel() == null;
    }

    @Override
    public int hashCode() {
        int result = (isNonExisting() ? 1 : 0);
        result = 31 * result + (getExistingLabel() != null ? getExistingLabel().hashCode() : 0);
        return result;
    }
}
