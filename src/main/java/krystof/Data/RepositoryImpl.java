package krystof.Data;

import krystof.business.Note;
import org.springframework.stereotype.Component;

/**
 * Created by polansky on 8.12.2017.
 */

@Component
public class RepositoryImpl implements Repository {
    @Override
    public Note findOne(long id) {
        return new Note("Mock");
    }
}
