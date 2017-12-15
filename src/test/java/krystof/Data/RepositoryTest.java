package krystof.Data;

import krystof.business.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
   private Repository repository;


    @Test
    public void findByLabel() throws Exception {

        repository.deleteAll();
        Note expectedNote1 = new Note("note1", "label1");
        Note expectedNote2 = new Note("note1", "label1");

        repository.save(expectedNote1);
        repository.save(expectedNote2);

        List<Note> actualNotes = repository.findByLabel("label1");
        assertTrue(actualNotes.size() == 2);
        assertTrue(actualNotes.containsAll(Arrays.asList(expectedNote1, expectedNote2)));



    }

}