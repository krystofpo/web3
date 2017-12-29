package krystof.Controller;

import krystof.business.Note;
import krystof.business.NoteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class NoteController {


    private NoteHandler handler;

    @Autowired
    public NoteController(NoteHandler handler) {
        this.handler = handler;
    }

    @RequestMapping(value="/note/{Id}", method = RequestMethod.GET)
    public @ResponseBody Note showNote(@PathVariable("Id") long id) {
        return handler.findOne(id);
    }


}
