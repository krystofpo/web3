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
    def "FindNoteByManyLabels #inputList"() {
        expect:
        noteHandler.findNoteByManyLabels(inputList) == expectedList
        noteHandler.findAllLabels().size() == 4
        noteHandler.findAllNotes().size() == 3

        where:
        inputList                         | expectedList
        null                              | []
        []                                | []
        [labelE]                          | [] //non existing label
        [labelC]                          | [note2, note3] //existing label
        [labelA, labelB]                  | [note1, note2] //exisitng labels
        [labelA, null, labelB]            | [] //invalid label with existing labels
        [labelA, new Label(null), labelB] | [] //invalid label with existing labels
        [labelA, new Label(''), labelB]   | [] //invalid label with existing labels
        [labelA, new Label('  '), labelB] | [] //invalid label with existing labels
        [labelA, labelD]                  | [] //existing labels but no note contains both
        [labelA, labelE]                  | [] //existing and non existing label
    }

    @Unroll
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

    @Unroll
    def "checkIfLabelExists"() {

        expect:
        noteHandler.checkIfLabelExists(label) == result

        where:
        label           | result
        //existing label:
        labelA          | new LabelWithFlag(false, labelA)
        //non existing valid label:
        labelE          | new LabelWithFlag(true, null)
        //invalid:
        new Label(null) | new LabelWithFlag(true, null)
        //invalid:
        new Label('')   | new LabelWithFlag(true, null)
        //invalid:
        new Label(' ')  | new LabelWithFlag(true, null)
//invalid
        null            | new LabelWithFlag(true, null)
    }

//    def "findLabelByLabel"() {
//        expect:
//        noteHandler.findLabelByLabel(label) == result
//
//        where:
//        label           |result
//        null            |null
//        new Label(null)     |null
//        new Label('')       |null
//        new Label(' ')      |null
//        //non exisitng:
//        labelE                  |null
//        //exisitng:
//        labelA                  |labelA
//    }
    @Unroll
    def "findLabelByLabel"() {
        expect:
        noteHandler.findLabelByLabel(label) == result

        where:
        label    | result
        null     | null
        ''       | null
        '   '    | null
        //non exisitng:
        'labelE' | null
        //exisitng:
        'labelA' | labelA
    }

    @Unroll
    def "findNoteByOneLabel"() {
        expect:
        noteHandler.findNoteByOneLabel(label) == result
        noteHandler.findAllLabels().size() == 4
        noteHandler.findAllNotes().size() == 3

        where:
        label               | result
        null                | []
        new Label(null)     | []
        new Label('')       | []
        new Label(' ')      | []
        //non existing:
        new Label("labelE") | []
        //existing but created without Id
        new Label('labelA') | [note1, note2]
    }

    @Unroll
    def "updateNote"() {

        given:
        Note realnote1 = noteHandler.findNoteByNote(note.getNote())
        realnote1.setNote(realnote1.getNote() + 'updated')
        Label lc = noteHandler.findLabelByLabel('labelC')
        realnote1.getLabels().clear()
        realnote1.getLabels().add(lc)

        expect:
        noteHandler.updateNote(realnote1) == result
        noteHandler.findAllNotes() == allNotes

        where:
        note                        | result                                             | allNotes
        //corect entity, removed label A, labelB, added C, changed note description
        new Note(1L, "note1", null) | new Note(10000L, "note1updated", [labelC].toSet()) | [new Note(1L, "note1updated", [labelC].toSet()), note2, note3]
    }

    @Unroll
    def "updateNote-throwing"() {
        when:
        noteHandler.updateNote(note)

        then:
        thrown(NoteHandlerException)


        where:
        note << [null,
                 //entity exists but is not persisted=no id
                 new Note('note1'),
                 //entity has id but id does not exist
                 new Note(1000L, "note8", null)]


    }
}
