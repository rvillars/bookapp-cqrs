package ch.bfh.swos.bookapp.cqrs.book;

import ch.bfh.swos.bookapp.cqrs.book.application.command.AddBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.ChangeBookTitleCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.RemoveBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.handler.BookCommandHandler;
import ch.bfh.swos.bookapp.cqrs.book.domain.Book;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookAddedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookRemovedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookTitleChangedEvent;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class BookEventTest {

    private FixtureConfiguration fixture;
    private String bookId;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Book.class);
        BookCommandHandler handler = new BookCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(handler);
        bookId = IdentifierFactory.getInstance().generateIdentifier();
    }

    @Test
    public void testAddBook() throws Exception {
        fixture.given()
                .when(new AddBookCommand(bookId, "Lord of the Rings - Return of the King", null, null))
                .expectEvents(new BookAddedEvent(bookId, "Lord of the Rings - Return of the King", null, null));
    }

    @Test
    public void testChangeBookTitle() throws Exception {
        fixture.given(new BookAddedEvent(bookId, "Lord of the Rings - Return of the King", null, null))
                .when(new ChangeBookTitleCommand(bookId, "Lord of the Rings - Fellowship of the Ring"))
                .expectEvents(new BookTitleChangedEvent(bookId, "Lord of the Rings - Fellowship of the Ring"));
    }

    @Test
    public void testRemoveBook() throws Exception {
        fixture.given(new BookAddedEvent(bookId, "Lord of the Rings - Return of the King", null, null))
                .when(new RemoveBookCommand(bookId))
                .expectEvents(new BookRemovedEvent(bookId));
    }
}
