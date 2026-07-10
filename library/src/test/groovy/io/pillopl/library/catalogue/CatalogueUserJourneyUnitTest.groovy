package io.pillopl.library.catalogue

import io.pillopl.library.commons.commands.Result
import io.pillopl.library.commons.events.DomainEvents
import io.vavr.control.Option
import spock.lang.Specification

class CatalogueUserJourneyUnitTest extends Specification {

    CatalogueDatabase catalogueDatabase = Stub()
    DomainEvents domainEvents = Mock()
    Catalogue catalogue = new Catalogue(catalogueDatabase, domainEvents)

    def 'should accept a valid book and create an instance after the book exists'() {
        given:
            catalogueDatabase.saveNew(_ as Book) >> null
            catalogueDatabase.saveNew(_ as BookInstance) >> null
            catalogueDatabase.findBy(new ISBN('9780201485677')) >> Option.of(new Book('9780201485677', 'Eric Evans', 'Domain-Driven Design'))
        when:
            def addBookResult = catalogue.addBook('Eric Evans', 'Domain-Driven Design', '9780201485677')
            def addInstanceResult = catalogue.addBookInstance('9780201485677', BookType.Restricted)
        then:
            addBookResult.isSuccess()
            addBookResult.get() == Result.Success
            addInstanceResult.isSuccess()
            addInstanceResult.get() == Result.Success
            1 * domainEvents.publish(_ as BookInstanceAddedToCatalogue)
    }

    def 'should reject adding an instance when the book is absent from the catalogue'() {
        given:
            catalogueDatabase.findBy(new ISBN('9780000000000')) >> Option.none()
        when:
            def result = catalogue.addBookInstance('9780000000000', BookType.Restricted)
        then:
            result.isSuccess()
            result.get() == Result.Rejection
            0 * domainEvents.publish(_ as BookInstanceAddedToCatalogue)
    }

    def 'should fail when the business data is invalid'() {
        when:
            def result = catalogue.addBook('Eric Evans', '   ', '9780201485677')
        then:
            result.isFailure()
    }
}
