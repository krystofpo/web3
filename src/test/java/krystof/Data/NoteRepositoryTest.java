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

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void oneNoteWithTwoLabels() {

        /*
        napred delete
        pak uloyzit

        napr pozn 1, labelA labelB
pozn 2, labelA, labelC

vyvolat z repository, otestovat


         */


        String labelAString = "labelA";
        Label labelA = new Label(labelAString);

        Label labelB = new Label("labelB");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        repository.deleteAll();
//

        repository.save(note1);
//        repository.save(note2);


        Note actualNote = repository.findOne(1L);
//        List<Note> actualNotes = repository.oneNoteWithTwoLabels(labelAString);
//        assertTrue(actualNotes.size() == 2);
//        assertTrue(actualNotes.containsAll(Arrays.asList(note1, note2)));

        System.out.println(actualNote);

//pokus
        //TODO
//
//        Label lA1=new Label("pokusA");
//        Label lA2=new Label("pokusA");
//        assertTrue(lA1.equals(lA2));
//
//
//        String a1 = "pokusA";
//        String a2 = "pokusA";
//        Label lA3=new Label(a1);
//        Label lA4=new Label(a2);
//        assertTrue(lA3.equals(lA4));
//
//        assertTrue(lA3.equals(lA1));

        assertTrue(areNotesEqual(note1, actualNote));

        //todo ze jsou labely stjene pomoci arenotesequal

    }

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
//

        repository.save(note1);
        repository.save(note2);


//        Note actualNote = repository.findOne(1L);
        List<Note> actualNotes = repository.findByLabel(labelAString);
        assertTrue(actualNotes.size() == 2);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1, note2)));

        System.out.println(actualNotes);

//pokus
        //TODO
//
//        Label lA1=new Label("pokusA");
//        Label lA2=new Label("pokusA");
//        assertTrue(lA1.equals(lA2));
//
//
//        String a1 = "pokusA";
//        String a2 = "pokusA";
//        Label lA3=new Label(a1);
//        Label lA4=new Label(a2);
//        assertTrue(lA3.equals(lA4));
//
//        assertTrue(lA3.equals(lA1));

//        assertTrue(areNotesEqual(note1, actualNote));

    }

    public static boolean areNotesEqual(Note note1, Note note2) {
        if (!note1.getNote().equals(note2.getNote())) {
            return false;
        }

        if (note1.getLabels().size() != note2.getLabels().size()) {
            return false;
        }

        if (note1.getLabels().containsAll(note2.getLabels())) {
            return true;
        }
        return false;
    }
}