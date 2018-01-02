package krystof.Controller;


public class PageParams {

    private String formAction;
    private String submitText;

    public PageParams(String formAction, String submitText) {
        this.formAction = formAction;
        this.submitText = submitText;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }
}
