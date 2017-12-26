package krystof.business

import krystof.Data.LabelRepository
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class NoteHandlerSpec extends Specification {

    private LabelRepository labelRepository = Mock()
    private NoteHandler noteHandler = new NoteHandler()

    @Shared
    def giveMeEmptyList = "giveMeEmptyList"
    @Shared
    def giveMeNull = "giveMeNull"
    @Shared
    def giveMeSaved = "giveMeSaved"
    @Shared
    def giveMeSaved2 = "giveMeSaved2"

    def setup() {
        noteHandler.setLabelRepository(labelRepository)
    }



@Unroll
    def "findSavedLabe... -  #labelList" (){
        given:

        labelRepository.findByLabel(giveMeEmptyList) >> []
        labelRepository.findByLabel(giveMeNull) >> null
        labelRepository.findByLabel(giveMeSaved) >> [new Label("Saved")]
        labelRepository.findByLabel(giveMeSaved2) >> [new Label("Saved2")]


        when:
        def flagList=noteHandler.findSavedLabelsOrFlagNonSavedLabel(labelList)

        then:
        flagList.containsNonSavedLabel() == contains
        flagList.savedLabels == list

        where:

        labelList                                               |contains   |list
        [new Label(giveMeNull)]                                 |true       |[]
        [new Label(giveMeEmptyList)]                            |true       |[]
        [new Label(giveMeSaved)]                                |false      |[new Label("Saved")]
        [new Label(giveMeSaved), new Label(giveMeSaved2)]       |false      |[new Label("Saved"), new Label("Saved2")]



    }


}