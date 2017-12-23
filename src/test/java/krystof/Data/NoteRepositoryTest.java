package krystof.Data;

import krystof.business.Label;
import krystof.business.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository repository;


    @Test
    public void findByLabel() throws Exception {

        String labelAString = "labelA";

        Label labelA = new Label(labelAString);
        Label labelA1 = new Label(labelAString);
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        Note note2 = new Note(
                "note2", new HashSet<Label>(Arrays.asList(
                labelA1, labelC)));

        repository.deleteAll();


        repository.save(note1);
        repository.save(note2);


        List<Note> actualNotes = repository.findByLabel(labelA);

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 2);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1, note2)));

    }

}