package krystof.Controller;

import krystof.Data.Repository;
import krystof.business.Note;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class NoteController {

    private Repository repository;

    public NoteController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/record/{Id}", method = RequestMethod.GET)
    public Note showNote(@PathVariable("Id") long id) {
        return repository.findOne(id);
    }


}
