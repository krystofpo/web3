package krystof.business

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class NoteHandlerSpec2 extends Specification {


    @Autowired
    private NoteHandler noteHandler = new NoteHandler()
//
//    @Shared
//    def giveMeEmptyList = "giveMeEmptyList"
//    @Shared
//    def giveMeNull = "giveMeNull"
//    @Shared
//    def giveMeSaved = "giveMeSaved"
//    @Shared
//    def giveMeSaved2 = "giveMeSaved2"
//    @Shared
//    def note1 = new Note(note: 'note1', labels: [new Label(giveMeSaved)])
//    @Shared
//    def note2 = new Note(note: 'note2', labels: [new Label(giveMeSaved), new Label(giveMeSaved2)])
//@Shared
//    def note3 = new Note(note: 'note3', labels: [new Label('someOther'), new Label(giveMeSaved2)])


    @Shared
    Label labelA = new Label("labelA");
    @Shared
    Label labelB = new Label("labelB");
    @Shared
    Label labelC = new Label("labelC");
    @Shared
    Label labelD = new Label("labelD");
    @Shared
    Label labelE = new Label("labelE");

    @Shared
    Note note1 = new Note(
            "note1", new HashSet<Label>(Arrays.asList(
            labelA, labelB)));

    @Shared
    Note note2 = new Note(
            "note2", new HashSet<Label>(Arrays.asList(
            labelA, labelB, labelC)));

    @Shared
    Note note3 = new Note(
            "note3", new HashSet<Label>(Arrays.asList(
            labelD, labelC)));

    void setup() {

        noteHandler.deleteAllNotes()
        noteHandler.deleteAllLabels()
        noteHandler.save(note1)
        noteHandler.save(note2)
        noteHandler.save(note3)
    }

    @Unroll
    def "FindByManyLabels #inputList"() {
        expect:
        noteHandler.findNoteByManyLabels(inputList) == expectedList

        where:
        inputList                               | expectedList
        null                                    | []
        []                                      | []
        [labelE]                                | []
        [labelC]                                | [note2, note3]
        [labelA, labelB]                        | [note1, note2]
        [labelA, null, labelB]                  | []
        [labelA, new Label(null), labelB]  | []
        [labelA, new Label(''), labelB]    | []
        [labelA, new Label('  '), labelB]  | []
        [labelA, labelD]                        | []
        [labelA, labelE]                        | []
    }

    def "findByNote"() {
        expect:
        noteHandler.findNoteByNote(noteDescription) == note

        where:
        noteDescription | note
        null            | null
        ''              | null
        '       '       | null
        'note'          | null
        'note1'         | note1
    }
}
