package krystof.business;

import krystof.Data.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by polansky on 12.12.2017.
 */
@Service
public class NoteHandler {
    @Autowired
    private Repository repository;

    public Note findOne(long id) {
        return repository.findOne(id);
    }
}
