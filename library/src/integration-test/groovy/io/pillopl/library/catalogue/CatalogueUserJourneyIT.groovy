package io.pillopl.library.catalogue

import io.pillopl.library.commons.commands.Result
import io.vavr.control.Option
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = CatalogueConfiguration.class)
class CatalogueUserJourneyIT extends Specification {

    @Autowired
    Catalogue catalogue

    @Autowired
    CatalogueDatabase catalogueDatabase

    def 'should persist a book and later create a valid instance through the full catalogue flow'() {
        given:
            String isbn = '9781234567890'
        when:
            def bookResult = catalogue.addBook('Eric Evans', 'Domain-Driven Design', isbn)
            def instanceResult = catalogue.addBookInstance(isbn, BookType.Restricted)
            Option<Book> persistedBook = catalogueDatabase.findBy(new ISBN(isbn))
        then:
            bookResult.isSuccess()
            bookResult.get() == Result.Success
            instanceResult.isSuccess()
            instanceResult.get() == Result.Success
            persistedBook.isDefined()
            persistedBook.get().getBookIsbn().getIsbn() == isbn
    }
}
