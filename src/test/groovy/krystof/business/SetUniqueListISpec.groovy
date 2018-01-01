package krystof.business

import spock.lang.Specification

/**
 * Created by polansky on 1.1.2018.
 */
class SetUniqueListISpec extends Specification {

    /*
    kdyz Spring zpracovava data z formulare a vytvari z nich entitu Note
    tak napred v Note.labels typu List vytvori indexy 0, 1 atd a do nich
    ulozi Labels ktery obsahujou Label.label=null. A protom vyvola Note.getlabels().get(1).setLabel
    a do nich uklada data z formulare.
    Ale protoze labels je objekt UniqueSetListI tak se chova jako Set, kdyz se pridavaji dalsi Labely
    s nullama, tak stare maze. Proto jsem zmenil equals u Labelu, aby Label.label = null a Label.label=null
    equals false. Tento test to testuje/
     */

    def "adding to Set Unique List I - keeps duplicates of Label.label=null"() {
        given:
        SetUniqueListI listI = new SetUniqueListI()

        when:
        listI.add(new Label(null))
        listI.add(new Label(null))
        listI.add(new Label(null))
        listI.add(new Label(null))

        then:
        listI.size() == 4

    }

    def "adding to list"() {
        given:
        SetUniqueListI listI = new SetUniqueListI()

        when:
        listI.add(label1)
        listI.add(label2)


        then:
        listI.size() == expSize

        where:
        label1               | label2                | expSize
        new Label(null) | new Label('')     | 2
        new Label(null) | new Label('neco') | 2
        new Label('')   | new Label('neco') | 2
    }

    def "set is not updated"() {
        given:
        List<Label> listI = new SetUniqueListI()
        listI.add(new Label(null))
        listI.add(new Label(null))
        listI.get(0).setLabel('')
        listI.get(1).setLabel('')

        expect:
        listI.size()==2
    }

    def "remove duplicates from list"() {
        given:
        List<Label> listI = new SetUniqueListI()
        listI.add(new Label(null))
        listI.add(new Label(null))
        listI.add(new Label(null))
        listI.get(0).setLabel('')
        listI.get(1).setLabel('a')
        listI.get(2).setLabel('a')

        when:
        listI.correctList()

        then:
        listI.size()==1
        listI.containsAll([new Label('a')])

    }


}
