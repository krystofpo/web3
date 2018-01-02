package krystof.utils

import spock.lang.Specification



class DataValidationServiceTest extends Specification {

    def service  = new DataValidationService()

    def "CorrectListOfStrings"() {

        expect:
        service.correctListOfStrings([null, 'a', null, "b", '', '    ']) == ['a', 'b']
    }
}
