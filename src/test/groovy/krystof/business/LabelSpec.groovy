package krystof.business

import spock.lang.Specification

class LabelSpec extends Specification {

    def "equals"() {

        expect:
        (label.equals(other)) == result

        where:
        label                  | other                   | result
        /*nasledujici test to je kvuli chovani
        SetUniqueList a Spring form data binding
        viz poznamka nad testem SetUniqueLsitI
         */
        new Label(null)   | new Label(null)     | false
        new Label(null)   | new Label('')       | false
        new Label('')     | new Label(null)     | false
        new Label('')     | new Label('')       | true
        new Label(null)   | new Label('neco')   | false
        new Label('neco') | new Label(null)     | false
        new Label('neco') | new Label('jinyho') | false
        new Label('neco') | new Label('neco')   | true
        new Label('')     | new Label('neco')   | false
        new Label('neco') | new Label('')       | false


    }

    def "validation"() {
        Label labelOK  = new Label('a')
        Label labelNOTOK  = new Label('')
        Label labelNOTOK1  = new Label(null)

        expect:
        labelOK.isValid() == true
        labelNOTOK.isValid() == false
        labelNOTOK1.isValid() == false

    }
}
