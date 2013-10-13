package ch.bfh.swos.bookapp.cqrs.author;

import ch.bfh.swos.bookapp.cqrs.author.application.command.AddAuthorCommand;
import ch.bfh.swos.bookapp.cqrs.author.application.command.RemoveAuthorCommand;
import ch.bfh.swos.bookapp.cqrs.author.application.handler.AuthorCommandHandler;
import ch.bfh.swos.bookapp.cqrs.author.domain.Author;
import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorAddedEvent;
import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorRemovedEvent;
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
public class AuthorEventTest {

    private FixtureConfiguration fixture;
    private String authorId;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Author.class);
        AuthorCommandHandler handler = new AuthorCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(handler);
        authorId = IdentifierFactory.getInstance().generateIdentifier();
    }

    @Test
    public void testAddAuthor() throws Exception {

        fixture.given()
                .when(new AddAuthorCommand(authorId, "H.R.R", "Tolkien"))
                .expectEvents(new AuthorAddedEvent(authorId, "H.R.R", "Tolkien"));
    }

    @Test
    public void testRemoveAuthor() throws Exception {
        fixture.given(new AuthorAddedEvent(authorId, "H.R.R", "Tolkien"))
                .when(new RemoveAuthorCommand(authorId))
                .expectEvents(new AuthorRemovedEvent(authorId));
    }
}
