package ch.bfh.swos.bookapp.cqrs.book.application.handler;

import ch.bfh.swos.bookapp.cqrs.book.application.command.AddBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.ChangeBookTitleCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.RemoveBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.domain.Book;
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
public class BookCommandHandler {

    @Inject
    @Named("eventBookRepository")
    private Repository<Book> repository;

    public BookCommandHandler() {
    }

    public BookCommandHandler(Repository<Book> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(AddBookCommand command) {
        System.out.println("Axon received AddBookCommand");
        Book book = new Book(command.getBookId(), command.getTitle(), command.getReleaseDate(), command.getAuthorId());
        repository.add(book);
    }

    @CommandHandler
    public void on(ChangeBookTitleCommand command) {
        System.out.println("Axon received ChangeBookTitleCommand");
        Book book = repository.load(command.getBookId());
        book.changeTitle(command.getNewTitle());
    }

    @CommandHandler
    public void on(RemoveBookCommand command) {
        System.out.println("Axon received RemoveBookCommand");
        Book book = repository.load(command.getBookId());
        book.remove();
    }
}
