package krystof.business

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class NoteHandlerSpec2 extends Specification {


@Autowired
    private NoteHandler noteHandler=new NoteHandler()

    @Shared
    def giveMeEmptyList = "giveMeEmptyList"
    @Shared
    def giveMeNull = "giveMeNull"
    @Shared
    def giveMeSaved = "giveMeSaved"
    @Shared
    def giveMeSaved2 = "giveMeSaved2"
    @Shared
    def note1 = new Note(note: 'note1', labels: [new Label(giveMeSaved)])
    @Shared
    def note2 = new Note(note: 'note2', labels: [new Label(giveMeSaved), new Label(giveMeSaved2)])
@Shared
    def note3 = new Note(note: 'note3', labels: [new Label('someOther'), new Label(giveMeSaved2)])



    void setup() {


        noteHandler.deleteAllNotes()
        noteHandler.deleteAllLabels()
        noteHandler.save(note1)
        noteHandler.save(note2)
        noteHandler.save(note3)
    }

@Unroll
    def "FindByLabels-all #inputList"() {
        expect:
        noteHandler.findByLabels(inputList) == expectedList

        where:
        inputList                                               |expectedList
        null                                                    |[]
        []                                                      |[]
        [new Label(giveMeNull)]                                 |[]
        [new Label(giveMeEmptyList)]                            |[]
        [new Label(giveMeSaved)]                                |[note1, note2]
        [new Label(giveMeSaved), new Label(giveMeSaved2)]       |[note2]

    }
}
