package krystof.Data;

import krystof.business.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface Repository extends CrudRepository<Note, Long>{

    List<Note> findByLabel(String label);
}
