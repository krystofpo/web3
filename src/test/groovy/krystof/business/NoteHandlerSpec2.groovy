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
            "note1", new ArrayList<Label>(Arrays.asList(
            labelA, labelB)));

    @Shared
    Note note2 = new Note(
            "note2", new ArrayList<Label>(Arrays.asList(
            labelA, labelB, labelC)));

    @Shared
    Note note3 = new Note(
            "note3", new ArrayList<Label>(Arrays.asList(
            labelD, labelC)));
    @Shared
    Note note4 = new Note(null, "abc def ghi", [labelA, labelB])

    void setup() {

        noteHandler.deleteAllNotes()
        noteHandler.deleteAllLabels()
        note1 = noteHandler.save(note1)
        note2 = noteHandler.save(note2)
        note3 = noteHandler.save(note3)
    }

    @Unroll
    def "FindNoteByManyLabels #inputList"() {

        when:
        noteHandler.deleteAllNotes()
        noteHandler.deleteAllLabels()
        note1 = noteHandler.save(note1)
        note2 = noteHandler.save(note2)
        note3 = noteHandler.save(note3)

        List actual = noteHandler.findNoteByManyLabels(inputList)

        then:
        actual.containsAll(expectedList)
        expectedList.containsAll(actual)
//        noteHandler.findNoteByManyLabels(inputList) == expectedList
        noteHandler.findAllLabels().size() == 4
        noteHandler.findAllNotes().size() == 3

        where:
        inputList                         | expectedList
        null                              | []
        []                                | []
        [labelE]                          | [] //non existing label
        [labelC]                          | [new Note("note2", [labelA, labelB, labelC]), new Note("note3", [labelD, labelC])] //existing label
        [labelA, labelB]                  | [new Note("note1", [labelA, labelB]), new Note("note2", [labelA, labelB, labelC])] //exisitng labels
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
    def "updateNote "() {
//during update the notehandler shoould remove duplicates and empty strings and nulls
        given:
        Note realnote1 = noteHandler.findNoteByNote(note.getNote())

        Note noteToUpdate = new Note(realnote1.getNoteId(), realnote1.getNote() + 'updated', null)
        Label savedA = noteHandler.findLabelByLabel('labelA')
        noteToUpdate.getLabels().add(savedA)
        noteToUpdate.getLabels().add(new Label(null))
        noteToUpdate.getLabels().add(new Label(null))
        noteToUpdate.getLabels().add(new Label(null))
        noteToUpdate.getLabels().add(new Label(null))
        noteToUpdate.getLabels().get(1).setLabel('dupl')
        noteToUpdate.getLabels().get(2).setLabel('dupl')
        noteToUpdate.getLabels().get(3).setLabel('')


        expect:
        noteHandler.updateNote(noteToUpdate) == result
        noteHandler.findAllNotes() == allNotes

        where:
        note                        | result                                                        | allNotes
        //corect entity, removed label A,  added unsaved label C, changed note description
        new Note(1L, "note1", null) | new Note(10000L, "note1updated", [labelA, new Label('dupl')]) | [new Note(1L, "note1updated", [labelA, new Label('dupl')]), note2, note3]
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


    def "updateLabel"() {

        given:
        Label realLabelA = noteHandler.findLabelByLabel('labelA')
        realLabelA.setLabel(realLabelA.getLabel() + 'updated')

        expect:
        noteHandler.updateLabel(realLabelA) == realLabelA
        noteHandler.findAllLabels().containsAll([realLabelA, labelB, labelC, labelD])

    }

    @Unroll
    def "updateLabel-throwing"() {
        when:
        noteHandler.updateLabel(label)

        then:
        thrown(NoteHandlerException)


        where:
        label << [null,
                  //entity exists but is not persisted=no id
                  new Label('labelA'),
                  //entity has id but id does not exist
                  new Label(1000L, "note8")]


    }

    @Unroll
    def "deleteLabel-throwing"() {

        when:
        noteHandler.deleteLabel(label)

        then:
        thrown(NoteHandlerException)

        where:
        label << [null,
                  //unsaved label = no id
                  new Label('labelA'),
                  //non existin label
                  new Label(1000L, 'labelA')
        ]
    }

    @Unroll
    def "deleteLabel"() {
        given:
        noteHandler.save(labelE)

        when:
        Label realLabel = noteHandler.findLabelByLabel(label.getLabel())
        noteHandler.deleteLabel(realLabel)
        def actualLabels = noteHandler.findAllLabels()
        def actualNotes = noteHandler.findAllNotes()

        then:
        actualLabels.containsAll(expectedLabels)
        actualLabels.size() == eSizeOfLabels
        actualNotes.containsAll(expectedNotes)
        actualNotes.size() == eSizeOfNotes

        where:
        label  | expectedLabels                   | eSizeOfLabels | expectedNotes                                                                                           | eSizeOfNotes
        //saved label not attached to any note, notes are unchangd
        labelE | [labelA, labelB, labelC, labelD] | 4             | [note1, note2, note3]                                                                                   | 3
        //saved label part of some notes, label is removed from corresponding notes
        labelA | [labelE, labelB, labelC, labelD] | 4             | [new Note('note1', [labelB]), new Note('note2', [labelB, labelC]), new Note('note3', [labelD, labelC])] | 3
    }

    @Unroll
    def "deleteNote-throwing"() {

        when:
        noteHandler.deleteNote(note)

        then:
        thrown(NoteHandlerException)

        where:
        note << [null,
                 //unsaved note = no id
                 new Note('note1'),
                 //non existin note
                 new Note(1000L, 'note1', null)
        ]
    }

    @Unroll
    def "deleteNote"() {


        when:
        Note realNote = noteHandler.findNoteByNote(note.getNote())
        noteHandler.deleteNote(realNote)
        def actualLabels = noteHandler.findAllLabels()
        def actualNotes = noteHandler.findAllNotes()

        then:
        actualLabels.containsAll(expectedLabels)
        actualLabels.size() == eSizeOfLabels
        actualNotes.containsAll(expectedNotes)
        actualNotes.size() == eSizeOfNotes

        where:
        note              | expectedLabels                   | eSizeOfLabels | expectedNotes  | eSizeOfNotes
        //labels are unchanged
        new Note('note1') | [labelA, labelB, labelC, labelD] | 4             | [note2, note3] | 2
        //labels are unchanged
        new Note('note3') | [labelA, labelB, labelC, labelD] | 4             | [note1, note2] | 2
    }

    def "remove duplicates and empty strings and nulls during save"() {
        given:
        Note note = new Note("pozn")

        note.getLabels().add(new Label(null))
        note.getLabels().add(new Label(null))
        note.getLabels().add(new Label(null))
        note.getLabels().add(new Label(null))
        note.getLabels().add(new Label(null))
        note.getLabels().get(0).setLabel('')
        note.getLabels().get(1).setLabel('a')
        note.getLabels().get(2).setLabel('a')
        note.getLabels().get(3).setLabel('b')

        when:
        note = noteHandler.save(note)

        then:
        note.getLabels().size() == 2
        note.getLabels().containsAll([new Label('b'), new Label('a')])
    }
@Unroll
    def "find Note by note containing a string #noteDescription"() {
        given:
        note4 = noteHandler.save(note4)

        when:
        List<Note> notes = noteHandler.findNotesByNoteContains(noteDescription)

        then:
        notes==expectedNotes

        where:
        noteDescription | expectedNotes
        'ote'           | [note1, this.note2, note3]
        'ote1'          | [note1]
        'de'            | [note4]
        'c d'           | [note4]
    }

    def "find Note by note containing a string -null "() {
        when:
        List<Note> notes = noteHandler.findNotesByNoteContains(note)

        then:
        notes == null

        where:
        note << [
                null,
                '',
                '   ']


    }

}
