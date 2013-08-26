package ch.bfh.swos.bookapp.cqrs.author.query;

import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorAddedEvent;
import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorRemovedEvent;
import ch.bfh.swos.bookapp.model.Author;
import ch.bfh.swos.bookapp.repository.AuthorRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 12.08.13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AuthorPersistenceEventListener {

    @Inject
    private AuthorRepository authorRepository;

    @EventHandler
    public void on(AuthorAddedEvent event) {
        System.out.println("Persistence listener reveived author added event for: " + event.getFirstname() + " " + event.getLastname() + " (" + event.getAuthorId() + ")");
        Author author = new Author();
        author.setAuthorId(event.getAuthorId());
        author.setFirstname(event.getFirstname());
        author.setLastname(event.getLastname());
        authorRepository.create(author);
        System.out.println("Author entity created");
    }

    @EventHandler
    public void on(AuthorRemovedEvent event) {
        System.out.println("Persistence listener reveived author removed event for: " + " (" + event.getAuthorId() + ")");
        Author author = authorRepository.readByAuthorId(event.getAuthorId());
        authorRepository.delete(author);
        System.out.println("Author entity deleted");
    }
}
