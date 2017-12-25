package krystof.business;

import krystof.Data.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteHandlerTest {

@Autowired
    private NoteHandler noteHandler;


//@MockBean
//    private NoteRepository mockRepository;

//    @Before
//    public void setUp() throws Exception {
//        when(mockRepository.findOne(anyLong())).thenReturn(new Note("mock2note", new HashSet<>(Arrays.asList(new Label("mock2Label")))));
//    }

//    @Test
//    public void findOne() throws Exception {
//
//        noteHandler.findOne(124L);
//                verify(mockRepository, times(1)).findOne(eq(124L));
//    }


    @Test
    public void noteHandlerHasAutowiredRepositories() throws Exception {
        assertTrue(noteHandler != null);
        assertTrue(noteHandler.getLabelRepository() != null);
        assertTrue(noteHandler.getNoteRepository() != null);
    }


    //integracni test
    @Test
    public void saveNewNoteWithNewAndExistingLabels() throws Exception {

        String labelAString = "labelA";

        Label labelA = new Label("labelA");
        Label labelA1 = new Label("labelA");
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        Note note2 = new Note(
                "note2", new HashSet<Label>(Arrays.asList(
                labelA1, labelC)));

        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        assertTrue(noteHandler.findAllNotes().size() ==0);
        assertTrue(noteHandler.findAllLabels().size() ==0);


        noteHandler.save(note1);
        noteHandler.save(note2);


        List<Note> actualNotes = noteHandler.findAllNotes();

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 2);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1, note2)));

        List<Label> allLabels = noteHandler.findAllLabels();

        System.out.println(allLabels);


        assertTrue(allLabels.size() == 3);
        assertTrue(allLabels.containsAll(Arrays.asList(labelA1, labelB, labelC)));

    }

    @Test
    public void saveExistingNoteWithSameLabels() throws Exception {

        String labelAString = "labelA";

        Label labelA = new Label("labelA");
        Label labelA1 = new Label("labelA");
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        Note note1copy = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA1, labelB)));

        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        assertTrue(noteHandler.findAllNotes().size() ==0);
        assertTrue(noteHandler.findAllLabels().size() ==0);


        noteHandler.save(note1);
        noteHandler.save(note1copy);


        List<Note> actualNotes = noteHandler.findAllNotes();

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 1);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1)));

        List<Label> allLabels = noteHandler.findAllLabels();

        System.out.println(allLabels);


        assertTrue(allLabels.size() == 2);
        assertTrue(allLabels.containsAll(Arrays.asList(labelA1, labelB)));


    }


    @Test
    public void saveExistingNoteWithDifferentLabels() throws Exception {

        String labelAString = "labelA";

        Label labelA = new Label("labelA");
        Label labelA1 = new Label("labelA");
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        Note note1copy1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA1, labelB, labelC)));

        Note note1copy2 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                        labelA)));

        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        assertTrue(noteHandler.findAllNotes().size() ==0);
        assertTrue(noteHandler.findAllLabels().size() ==0);


        noteHandler.save(note1);
        noteHandler.save(note1copy1);
        noteHandler.save(note1copy2);


        List<Note> actualNotes = noteHandler.findAllNotes();

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 1);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1)));

        List<Label> allLabels = noteHandler.findAllLabels();

        System.out.println(allLabels);


        assertTrue(allLabels.size() == 3);
        assertTrue(allLabels.containsAll(Arrays.asList(labelA1, labelB, labelC)));

        //todo hange save note so that first it checks if note exists and if not
        //then prepare labels and saves them
        //if it exists do not save labels

    }

    //todo new note exisitng labels
    //todo exisitng note same labels
    //todo exisitng note different labels

}