package krystof.Data;

import krystof.business.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface NoteRepository extends CrudRepository<Note, Long> {
//    @Query("SELECT a from Note a where ?1 member of a.labels")
//    List<Note> findByLabel(Label label);

    List<Note> findByNote(String note);

    List<Note> findAll();

}
