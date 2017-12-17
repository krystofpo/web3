package krystof.business;

import krystof.Data.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by polansky on 12.12.2017.
 */
@Service
public class NoteHandler {
    @Autowired
    private NoteRepository repository;

    public Note findOne(long id) {
        System.out.println(repository.toString());
//        return repository.findOne(id);
        repository.deleteAll();
        repository.save(new Note("notePokus","pokus"));
        return repository.findByLabel("pokus").get(0);
    }
}
