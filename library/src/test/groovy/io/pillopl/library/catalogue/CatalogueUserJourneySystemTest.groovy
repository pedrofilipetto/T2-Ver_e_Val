package io.pillopl.library.catalogue

import io.pillopl.library.commons.commands.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = CatalogueConfiguration.class)
class CatalogueUserJourneySystemTest extends Specification {

    @Autowired
    Catalogue catalogue

    def 'should support the end-to-end catalog journey for a valid user request'() {
        given:
            String isbn = '1111111111'
        when:
            def bookResult = catalogue.addBook('Martin Fowler', 'Refactoring', isbn)
            def instanceResult = catalogue.addBookInstance(isbn, BookType.Restricted)
        then:
            bookResult.isSuccess()
            bookResult.get() == Result.Success
            instanceResult.isSuccess()
            instanceResult.get() == Result.Success
    }
}
