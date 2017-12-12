package krystof.Data;

import krystof.business.Note;

/**
 * Created by polansky on 8.12.2017.
 */
public interface Repository {
    Note findOne(long id);
}
