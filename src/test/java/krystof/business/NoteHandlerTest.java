package krystof.business;

import krystof.Data.LabelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
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
                    "note1", new HashSet<Label>(Arrays.asList(
                    labelA, labelB)));

            Note note1copy1 = new Note(
                    "note1", new HashSet<Label>(Arrays.asList(
                    labelA1, labelB, labelC)));


            noteHandler.deleteAllNotes();
            noteHandler.deleteAllLabels();


            noteHandler.save(note1);
            noteHandler.save(note1copy1);
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
    public void findNoteByLabel() throws Exception {

        Label labelA = new Label("labelA");
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");
        Label labelD = new Label("labelD");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        Note note2 = new Note(
                "note2", new HashSet<Label>(Arrays.asList(
                labelA, labelC)));

        Note note3 = new Note(
                "note3", new HashSet<Label>(Arrays.asList(
                labelD, labelC)));


        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        noteHandler.save(note1);
        noteHandler.save(note2);
        noteHandler.save(note3);


        List<Note> actualNotes = noteHandler.findByLabel(labelA);

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 2);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1, note2)));

        List<Label> allLabels = noteHandler.findAllLabels();

        System.out.println(allLabels);


        assertTrue(allLabels.size() == 4);
        assertTrue(allLabels.containsAll(Arrays.asList(labelA, labelB, labelC, labelD)));


    }

    @Test
    public void saveLabelAndNotewithExistingLabel() throws Exception {

        Label labelA = new Label("labelA");
        Label labelB = new Label("labelB");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));


        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        noteHandler.save(labelA);
        noteHandler.save(note1);


        List<Note> actualNotes = noteHandler.findByLabel(labelA);

        System.out.println(actualNotes);


        assertTrue(actualNotes.size() == 1);
        assertTrue(actualNotes.containsAll(Arrays.asList(note1)));

        List<Label> allLabels = noteHandler.findAllLabels();

        System.out.println(allLabels);


        assertTrue(allLabels.size() == 2);
        assertTrue(allLabels.containsAll(Arrays.asList(labelA, labelB)));


    }




    //todo find note by labels list

    @Test
    public void findNoteByLabelsMultiple() throws Exception {
        Label labelA = new Label("labelA");
        Label labelB = new Label("labelB");
        Label labelC = new Label("labelC");
        Label labelD = new Label("labelD");
        Label labelE = new Label("labelE");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA, labelB)));

        Note note2 = new Note(
                "note2", new HashSet<Label>(Arrays.asList(
                labelA, labelB, labelC)));

        Note note3 = new Note(
                "note3", new HashSet<Label>(Arrays.asList(
                labelD, labelC)));


        noteHandler.deleteAllNotes();
        noteHandler.deleteAllLabels();

        noteHandler.save(note1);
        noteHandler.save(note2);
        noteHandler.save(note3);


        List<Note> actualNotes1 = noteHandler.findByLabels(Arrays.asList(labelC));

        System.out.println(actualNotes1);


        assertTrue(actualNotes1.size() == 2);
        assertTrue(actualNotes1.containsAll(Arrays.asList(note2, note3)));

       assertTrue(noteHandler.findByLabels(Arrays.asList(labelE)).size() == 0);
       assertTrue(noteHandler.findByLabels(null).size() == 0);

        List<Note> actualNotes2 = noteHandler.findByLabels(Arrays.asList(labelB, labelA));

        System.out.println(actualNotes2);


        assertTrue(actualNotes2.size() == 2);
        assertTrue(actualNotes1.containsAll(Arrays.asList(note2, note1)));


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