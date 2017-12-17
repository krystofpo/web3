package krystof.Data;

import krystof.business.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface NoteRepository extends CrudRepository<Note, Long> {

    //List<Note> findByLabel(String label);

Note findOne(Long id);


    void deleteAll();

     Note save(Note note);
}
