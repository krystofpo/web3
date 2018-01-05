package krystof.Controller;

import krystof.business.Label;
import krystof.business.NoteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class LabelController {

    private final static String SAVE_LABEL = "savelabel";
    private final static String LABEL_RESULT = "labelresult";
    private final static String LABELS_MODEL_ATTRIBUTE = "labels";
    private static final String EDIT_LABEL_ID = "/edit/label/{Id}";
    private static final String FIND_LABELS_CONTAINS = "/findlabelscontains";
    private final static String FIND_LABEL_BY_LABEL = "/findlabel";


    private NoteHandler handler;

    @Autowired
    public LabelController(NoteHandler handler) {
        this.handler = handler;
    }



    @RequestMapping(value = "/" + SAVE_LABEL, method = RequestMethod.GET)
    public String showSaveLabelForm(Model model) {

        model.addAttribute("label", new Label(""));
        model.addAttribute("formAction", SAVE_LABEL);


        return SAVE_LABEL;
    }

    @RequestMapping(value = "/" + SAVE_LABEL, method = RequestMethod.POST)
    public String submitLabel(@ModelAttribute(name = "labelentity") Label label, Model model) {

        System.out.println("------------------------\n\n\n\n\n\n\n\n");
        System.out.println(label);

//        System.out.println(model);
        System.out.println("ulozim");
        Label labelObject = handler.saveLabel(label.getLabel());
        System.out.println(labelObject);
        model.addAttribute(LABELS_MODEL_ATTRIBUTE, Arrays.asList(labelObject));
//        System.out.println(model);

//
//        System.out.println(multiMap);
//        multiMap.get("labels.label").forEach(val -> System.out.println(val.toString()));


        System.out.println("------------------------\n\n\n\n\n\n\n\n");

        return LABEL_RESULT;
    }
//
//    @RequestMapping(value = "/" + FIND_LABEL_BY_LABEL, method = RequestMethod.GET)
//    public String showFindNoteByNoteExactForm(Model model)
//    {
//        PageParams page = new PageParams(FIND_LABEL_BY_LABEL, "Find");
////        model.addAttribute("labelentity", savedLabel);
////        System.out.println(model);
////        //todo save it, ale jak to predat view jako novy objekt? lepsi
////        //by bzlo psmeroa tna label a id a dat to tomu id jako parametr
//        return "result";
//    }
//
//    @RequestMapping(value = "/" + FIND_NOTES_BY_NOTE_CONTAINS, method = RequestMethod.GET)
//    public String showFindNotesByNoteContainsForm(Model model)
//    {
//        PageParams page = new PageParams(FIND_NOTES_BY_NOTE_CONTAINS, "Find");
//        model.addAttribute("page", page);
//        return FIND_LABEL_BY_LABEL;
//    }
//
//    @RequestMapping(value = "/" + FIND_NOTES_BY_NOTE_CONTAINS, method = RequestMethod.POST)
//    public String showFindNotesByNoteContainsResult(
//            @RequestParam("note") String note, Model model) {
//        System.out.println("note search" + note);
//        List<Note> notesReal = handler.findNotesByNoteContains(note);
//        System.out.println("found" + notesReal);
//        if (notesReal != null) {
//            model.addAttribute(LABELS_MODEL_ATTRIBUTE, notesReal);
//            return LABEL_RESULT;
//
//
//        }
//
//    }
//
//

        @RequestMapping(value = "/alllabels", method = RequestMethod.GET)
        public String showAllLabels(Model model) {
            List<Label> allLabels = handler.findAllLabels();

                model.addAttribute(LABELS_MODEL_ATTRIBUTE, allLabels);

            return LABEL_RESULT;
        }


    @RequestMapping(value = "/delete/label/{Id}", method = RequestMethod.GET)
    public String deleteLabel(@PathVariable("Id") long id) {
        handler.deleteLabel(id);

        return "redirect:/";
    }


    @RequestMapping(value = EDIT_LABEL_ID, method = RequestMethod.GET)
    public String showEditLabelForm(@PathVariable("Id")long id, Model model) {

        Label label = handler.findLabel(id);
        model.addAttribute("label", label);
        model.addAttribute("formAction", EDIT_LABEL_ID.replace("{Id}", String.valueOf(id)));

        return SAVE_LABEL;
    }


    @RequestMapping(value = EDIT_LABEL_ID, method = RequestMethod.POST)
    public String submitEditLabel(@ModelAttribute(name = "labelentity") Label label, Model model) {

        System.out.println("------------------------\n\n\n\n\n\n\n\n");
        System.out.println(label);

//        System.out.println(model);
        System.out.println("upd");
        label = handler.updateLabel(label);
        System.out.println(label);
        model.addAttribute(LABELS_MODEL_ATTRIBUTE, Arrays.asList(label));
//        System.out.println(model);

//
//        System.out.println(multiMap);
//        multiMap.get("labels.label").forEach(val -> System.out.println(val.toString()));


        System.out.println("------------------------\n\n\n\n\n\n\n\n");

        return LABEL_RESULT;
    }

    @RequestMapping(value = FIND_LABELS_CONTAINS, method = RequestMethod.GET)
    public String showFindLabelsByLabelContainsForm(Model model)
    {
        PageParams page = new PageParams(FIND_LABELS_CONTAINS, "Find");
        model.addAttribute("page", page);
        return NoteController.FIND_NOTE_BY_NOTE;
    }

    @RequestMapping(value =FIND_LABELS_CONTAINS, method = RequestMethod.POST)
    public String showFindLabelsByLabelContainsResult(
            @RequestParam("note") String label, Model model) {
        System.out.println("label search" + label);
        List<Label> labelsReal = handler.findLabelsByLabelContains(label);
        System.out.println("found" + labelsReal);
        if (labelsReal != null) {
            model.addAttribute(LABELS_MODEL_ATTRIBUTE, labelsReal);


        }return LABEL_RESULT;}


    @RequestMapping(value =  FIND_LABEL_BY_LABEL, method = RequestMethod.GET)
    public String showFindNoteByNoteExactForm(Model model)
    {
        PageParams page = new PageParams(FIND_LABEL_BY_LABEL, "Find");
        model.addAttribute("page", page);
        return NoteController.FIND_NOTE_BY_NOTE;
    }

    @RequestMapping(value = FIND_LABEL_BY_LABEL, method = RequestMethod.POST)
    public String showFindLabelByLabelExactResult(
            @RequestParam("note") String label, Model model) {
        Label labelReal = handler.findLabelByLabel(label);
        System.out.println("found" + labelReal);
        if (labelReal != null) {
            model.addAttribute(LABELS_MODEL_ATTRIBUTE, Arrays.asList(labelReal));
            return LABEL_RESULT;
        }
        return LABEL_RESULT;
    }

}