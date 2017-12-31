package krystof.business;

import krystof.Data.LabelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteHandlerTest {

@Autowired
    private NoteHandler noteHandler;



    @Test
    public void noteHandlerHasAutowiredRepositories() throws Exception {
        assertTrue(noteHandler != null);
        assertTrue(noteHandler.getLabelRepository() != null);
        assertTrue(noteHandler.getNoteRepository() != null);
    }



    //integracni test
    @Test
    public void saveNewNoteWithNewAndExistingLabels() throws Exception {


        Label labelA = new Label("labelA");
        Label labelA1 = new Label("labelA");
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");

        Note note1 = new Note(
                "note1", new ArrayList<Label>(Arrays.asList(
                labelA, labelB)));

        Note note2 = new Note(
                "note2", new ArrayList<Label>(Arrays.asList(
                labelA1, labelC)));

        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        assertTrue(noteHandler.findAllNotes().size() ==0);
        assertTrue(noteHandler.findAllLabels().size() ==0);


        note1 = noteHandler.save(note1);
        note2 = noteHandler.save(note2);


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
    public void saveExistingNoteShouldThrow() throws Exception {

        Label labelA1 = null;
        Label labelB = null;
        Label labelC = null;
        Note note1 = null;
        try {

            Label labelA = new Label("labelA");
            labelA1 = new Label("labelA");
            labelB = new Label("labelB");
            labelC = new Label("labelC");

            note1 = new Note(
                    "note1", new ArrayList<Label>(Arrays.asList(
                    labelA, labelB)));

            Note note1copy1 = new Note(
                    "note1", new ArrayList<Label>(Arrays.asList(
                    labelA1, labelB, labelC)));


            noteHandler.deleteAllNotes();
            noteHandler.deleteAllLabels();


            note1 = noteHandler.save(note1);
            note1copy1 = noteHandler.save(note1copy1);
            fail();
        } catch (NoteHandlerException e) {
            assertThat(e.getMessage(), is(
                    "Error: Attempt to save a note which " +
                            "already exists. Use update instead."));

            List<Note> actualNotes = noteHandler.findAllNotes();

            System.out.println(actualNotes);


            assertTrue(actualNotes.size() == 1);
            assertTrue(actualNotes.containsAll(Arrays.asList(note1)));

            List<Label> allLabels = noteHandler.findAllLabels();

            System.out.println(allLabels);


            assertTrue(allLabels.size() == 2);
            assertTrue(allLabels.containsAll(Arrays.asList(labelA1, labelB)));
        }
    }


    @Test
    public void saveLabelAndNotewithExistingLabelAndNewLabel() throws Exception {

        Label labelA = new Label("labelA");
        Label labelB = new Label("labelB");

        Note note1 = new Note(
                "note1", new ArrayList<Label>(Arrays.asList(
                labelA, labelB)));


        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        noteHandler.save(labelA);
        note1 = noteHandler.save(note1);


        List<Note> actualNotes = noteHandler.findNoteByOneLabel(labelA);

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 1);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1)));

        List<Label> allLabels = noteHandler.findAllLabels();

        System.out.println(allLabels);


        assertTrue(allLabels.size() == 2);
        assertTrue(allLabels.containsAll(Arrays.asList(labelA, labelB)));


    }





    @Test
    public void pokus() throws Exception {

        LabelRepository labelRepository = noteHandler.getLabelRepository();

        List<Label> labels = labelRepository.findByLabel("nonexitent");

        assertTrue(labels != null);
        assertTrue(labels.size()==0);

    }


    //todo  how is  exception of saving exisitng note going to be handled
    //and shown on a webpage? transaction will rollback? add @transcaiotnal? where?

}