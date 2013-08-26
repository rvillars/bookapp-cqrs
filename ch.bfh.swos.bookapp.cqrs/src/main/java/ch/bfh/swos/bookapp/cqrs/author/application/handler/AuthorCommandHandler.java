package ch.bfh.swos.bookapp.cqrs.author.application.handler;

import ch.bfh.swos.bookapp.cqrs.author.application.command.AddAuthorCommand;
import ch.bfh.swos.bookapp.cqrs.author.application.command.RemoveAuthorCommand;
import ch.bfh.swos.bookapp.cqrs.author.domain.Author;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AuthorCommandHandler {

    @Inject
    @Named("eventAuthorRepository")
    private Repository<Author> repository;


    public AuthorCommandHandler() {
    }

    public AuthorCommandHandler(Repository<Author> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(AddAuthorCommand command) {
        System.out.println("Axon received AddAuthorCommand");
        Author author = new Author(command.getAuthorId(), command.getFirstname(), command.getLastname());
        repository.add(author);
    }

    @CommandHandler
    public void on(RemoveAuthorCommand command) {
        System.out.println("Axon received RemoveAuthorCommand");
        Author author = repository.load(command.getAuthorId());
        author.remove();
    }
}
