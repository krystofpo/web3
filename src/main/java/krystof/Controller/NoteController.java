package krystof.Controller;

import krystof.business.Note;
import krystof.business.NoteHandler;
import krystof.utils.DataValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller

public class NoteController {

    private final static String SAVE_NOTE = "savenote";
    private final static String NOTE_RESULT = "noteresult";
    private final static String NOTES_MODEL_ATTRIBUTE = "notes";
    final static String FIND_NOTE_BY_NOTE = "findnotebynote";
    private final static String FIND_NOTES_BY_NOTE_CONTAINS = "findnotesbynotecontains";
    private static final String FIND_NOTES_BY_LABELS = "findnotesbylabels";
    private static final String FIND_NOTES_BY_LABELS_CONTAINS = "findnotesbylabelscontains";
    private static final String EDIT_NOTE_ID = "/edit/note/{Id}";

    private NoteHandler handler;

    @Autowired
    private DataValidationService dataValidationService;

    @Autowired
    public NoteController(NoteHandler handler) {
        this.handler = handler;
    }

    @RequestMapping(value = "/note/{Id}", method = RequestMethod.GET)
    public @ResponseBody
    Note showNote(@PathVariable("Id") long id) {
        return handler.findNote(id);
    }


    @RequestMapping(value = "/" + SAVE_NOTE, method = RequestMethod.GET)
    public String showSaveNoteForm(Model model) {
        model.addAttribute("note", new Note(null, "", new ArrayList<>()));
        model.addAttribute("formAction", SAVE_NOTE);

        return SAVE_NOTE;
    }

    @RequestMapping(value = "/" + SAVE_NOTE, method = RequestMethod.POST)
    public String submitNote(@ModelAttribute(name = "noteentity") Note note, Model model) {

        System.out.println("------------------------\n\n\n\n\n\n\n\n");
        System.out.println(note);

//        System.out.println(model);
        System.out.println("ulozim");
        note = handler.save(note);
        System.out.println(note);
        model.addAttribute(NOTES_MODEL_ATTRIBUTE, Arrays.asList(note));
//        System.out.println(model);

//
//        System.out.println(multiMap);
//        multiMap.get("labels.label").forEach(val -> System.out.println(val.toString()));


        System.out.println("------------------------\n\n\n\n\n\n\n\n");

        return NOTE_RESULT;
    }

    @RequestMapping(value = "/" + FIND_NOTE_BY_NOTE, method = RequestMethod.GET)
    public String showFindNoteByNoteExactForm(Model model)
    {
        PageParams page = new PageParams(FIND_NOTE_BY_NOTE, "Find");
        model.addAttribute("page", page);
    return FIND_NOTE_BY_NOTE;
    }

    @RequestMapping(value = "/" + FIND_NOTE_BY_NOTE, method = RequestMethod.POST)
    public String showFindNoteByNoteExactResult(
            @RequestParam("note") String note, Model model) {
        System.out.println("note search" + note);
        Note noteReal = handler.findNoteByNote(note);
        System.out.println("found" + noteReal);
        if (noteReal != null) {
            model.addAttribute(NOTES_MODEL_ATTRIBUTE, Arrays.asList(noteReal));
            return NOTE_RESULT;
        }
        return NOTE_RESULT;
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
        return NOTE_RESULT;



    }



        @RequestMapping(value = "/allnotes", method = RequestMethod.GET)
        public String showAllNotes(Model model) {
            List<Note> allNotes = handler.findAllNotes();

                model.addAttribute(NOTES_MODEL_ATTRIBUTE, allNotes);

            return NOTE_RESULT;
        }



    @RequestMapping(value = "/" + FIND_NOTES_BY_LABELS, method = RequestMethod.GET)
    public String showFindNotesByLabelsForm(Model model)
    {
        PageParams page = new PageParams(FIND_NOTES_BY_LABELS, "Find");
        model.addAttribute("page", page);
        return FIND_NOTES_BY_LABELS;
    }




    @RequestMapping(value = "/" + FIND_NOTES_BY_LABELS, method = RequestMethod.POST)
    public String showFindNotesByLabelsResult(
            @RequestParam("labels") List<String> labels, Model model) {
        System.out.println("labels " + labels);
        System.out.println("size " + labels.size());
        List<Note> notesReal = handler.findNotesByManyLabelsString(labels);
        System.out.println("found" + notesReal);
        if (notesReal != null) {
            model.addAttribute(NOTES_MODEL_ATTRIBUTE, notesReal);
            return NOTE_RESULT;


        }
        return NOTE_RESULT;



    }


    @RequestMapping(value = "/" + FIND_NOTES_BY_LABELS_CONTAINS, method = RequestMethod.GET)
    public String showFindNotesByLabelsContainsForm(Model model)
    {
        PageParams page = new PageParams(FIND_NOTES_BY_LABELS_CONTAINS, "Find");
        model.addAttribute("page", page);
        return FIND_NOTES_BY_LABELS;
    }




    @RequestMapping(value = "/" + FIND_NOTES_BY_LABELS_CONTAINS, method = RequestMethod.POST)
    public String showFindNotesByLabelsContainsResult(
            @RequestParam("labels") List<String> labels, Model model) {
        System.out.println("labels " + labels);
        System.out.println("size " + labels.size());
        List<Note> notesReal = handler.findNotesByManyLabelsContainsString(labels);
        System.out.println("found" + notesReal);
        if (notesReal != null) {
            model.addAttribute(NOTES_MODEL_ATTRIBUTE, notesReal);
            return NOTE_RESULT;


        }
        return NOTE_RESULT;



    }



    @RequestMapping(value = "/delete/note/{Id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable("Id") long id) {
        handler.deleteNote(id);

        return "redirect:/";
    }



    @RequestMapping(value = EDIT_NOTE_ID, method = RequestMethod.GET)
    public String showEditNoteForm(@PathVariable("Id")long id, Model model) {

        Note note = handler.findNote(id);
        model.addAttribute("note", note);
        model.addAttribute("formAction", EDIT_NOTE_ID.replace("{Id}", String.valueOf(id)));

        return SAVE_NOTE;
    }



    @RequestMapping(value = EDIT_NOTE_ID, method = RequestMethod.POST)
    public String submitEditNote(@ModelAttribute(name = "noteentity") Note note, Model model) {

        System.out.println("------------------------\n\n\n\n\n\n\n\n");
        System.out.println(note);

//        System.out.println(model);
        System.out.println("ulozim");
        note = handler.updateNote(note);
        System.out.println(note);
        model.addAttribute(NOTES_MODEL_ATTRIBUTE, Arrays.asList(note));
//        System.out.println(model);

//
//        System.out.println(multiMap);
//        multiMap.get("labels.label").forEach(val -> System.out.println(val.toString()));


        System.out.println("------------------------\n\n\n\n\n\n\n\n");

        return NOTE_RESULT;
    }

}

