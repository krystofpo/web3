package krystof.Data;

import krystof.business.Label;
import krystof.business.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface NoteRepository extends CrudRepository<Note, Long> {
    @Query("SELECT a from Note a where ?1 member of a.labels")
    List<Note> findByLabel(Label label);
}
