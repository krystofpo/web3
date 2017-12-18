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

    @Value("${nejakavar}")
    private String neco;

    @Value("${spring.h2.console.enabled}")
    private boolean necob;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByLabel() {

        /*
        napred delete
        pak uloyzit

        napr pozn 1, labelA labelB
pozn 2, labelA, labelC

vyvolat z repository, otestovat


         */


        String labelAString = "labelA";
        Label labelA1 = new Label(labelAString);
//        Label labelA2 = new Label("labelA");
//        Label labelC = new Label("labelC");
        Label labelB = new Label("labelB");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                        labelA1, labelB)));

        labelA1.setNotes(new HashSet<Note>());
        labelB.setNotes(new HashSet<Note>());
        labelA1.getNotes().add(note1);
        labelB.getNotes().add(note1);
//        Note note2 = new Note(
//                "note2", new HashSet<Label>(Arrays.asList(
//                        labelA1, labelC)));

        repository.deleteAll();
//

        repository.save(note1);
//        repository.save(note2);


        Note actualNote = repository.findOne(1L);
//        List<Note> actualNotes = repository.findByLabel(labelAString);
//        assertTrue(actualNotes.size() == 2);
//        assertTrue(actualNotes.containsAll(Arrays.asList(note1, note2)));

        System.out.println(actualNote);

        System.out.println(neco);
        System.out.println(necob);

        Scanner scanner = new Scanner(System.in);
//...
        String name = scanner.nextLine();

        //todo ze jsou labely stjene pomoci arenotesequal

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