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

    def "FindByLabels - null"() {
        expect:
        noteHandler.findByLabels(null) == []

//poslu handleru praydny list ocekavam praydny list
//poslu handleru null ocekavam praydny list
        //polsu handlerovi list s jednim saved lableem
        //ocekavam ze vrati objekt v kterym bude contians false
        // a bude mit list s tim samym prvkem

        //poslu mu list s ejdnim neulozenym a pocekavam
        //ze vrati objetk s contians true

        //dva prvky, prvni ulozenej a druhej neulozenj
        //ocekavam ze vrati cotnains true

        //dva rpvky oba ulozeny
        //ocekavam ze vrati obejt coantians false a
        //budou v nem ulozeny prvky.

    }


    def "FindByLabels-empty"() {
        expect:
        noteHandler.findByLabels([]) == []
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