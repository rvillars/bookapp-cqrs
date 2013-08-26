package ch.bfh.swos.bookapp.cqrs.author.domain;

import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorAddedEvent;
import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorRemovedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class Author extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String authorId;

    private String firstname;
    private String lastname;

    public Author() {
    }

    public Author(String authorId, String firstname, String lastname) {
        apply(new AuthorAddedEvent(authorId, firstname, lastname));
    }

    public void remove() {
        apply(new AuthorRemovedEvent(authorId));
    }

    @EventHandler
    public void on(AuthorAddedEvent event) {
        System.out.println("AuthorAddedEvent called on Author");
        this.authorId = event.getAuthorId();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
    }

    @EventHandler
    public void on(AuthorRemovedEvent event) {
        System.out.println("AuthorRemovedEvent called on Author");
    }
}
