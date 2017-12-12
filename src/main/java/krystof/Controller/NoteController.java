package krystof.Controller;

import krystof.Data.Repository;
import krystof.business.Note;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/")
public class NoteController {

    private Repository repository;

    public NoteController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/note/{Id}", method = RequestMethod.GET)
    public @ResponseBody Note showNote(@PathVariable("Id") long id) {
        return repository.findOne(id);
    }


}
