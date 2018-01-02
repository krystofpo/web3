package krystof.Controller;

import krystof.business.Label;
import krystof.business.Note;
import krystof.business.NoteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LabelController {


    private NoteHandler handler;

    @Autowired
    public LabelController(NoteHandler handler) {
        this.handler = handler;
    }

    @RequestMapping(value = "/savelabel", method = RequestMethod.GET)
    public String showSaveLabelForm(Model model) {
        model.addAttribute("labelentity", new Label());
        return "savelabel";
    }

    @RequestMapping(value = "/savelabel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitLabel(@ModelAttribute(name="labelentity") Label label, Model model) {
        System.out.println(model);
        System.out.println("ulozim");
        Label label2 = handler.save(label);
        System.out.println("pubovdni label po ulozeni" + label);
        model.addAttribute("savedLabel", label);
        System.out.println(model);
        //        System.out.println(label);
//        Label savedLabel = handler.save(label);
//        System.out.println("saved" + savedLabel);
//        System.out.println(model);
//        model.addAttribute("labelentity", savedLabel);
//        System.out.println(model);
//        //todo save it, ale jak to predat view jako novy objekt? lepsi
//        //by bzlo psmeroa tna label a id a dat to tomu id jako parametr
        return "result";
    }

    @RequestMapping(value = "/" + FIND_NOTES_BY_NOTE_CONTAINS, method = RequestMethod.GET)
    public String showFindNotesByNoteContainsForm(Model model)
    {
        PageParams page = new PageParams(FIND_NOTES_BY_NOTE_CONTAINS, "Find");
        model.addAttribute("page", page);
        return FIND_NOTE_BY_NOTE;
    }

    @RequestMapping(value = "/" + FIND_NOTES_BY_NOTE_CONTAINS, method = RequestMethod.POST)
    public String showFindNotesByNoteContainsResult(
            @RequestParam("note") String note, Model model) {
        System.out.println("note search" + note);
        List<Note> notesReal = handler.findNotesByNoteContains(note);
        System.out.println("found" + notesReal);
        if (notesReal != null) {
            model.addAttribute(NOTES_MODEL_ATTRIBUTE, notesReal);
            return NOTE_RESULT;


        }

}