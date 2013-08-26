package ch.bfh.swos.bookapp.cqrs.book.query;

import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookAddedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookRemovedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookTitleChangedEvent;
import ch.bfh.swos.bookapp.model.Author;
import ch.bfh.swos.bookapp.model.Book;
import ch.bfh.swos.bookapp.repository.AuthorRepository;
import ch.bfh.swos.bookapp.repository.BookRepository;
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
public class BookPersistenceEventListener {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private AuthorRepository authorRepository;

    @EventHandler
    public void on(BookAddedEvent event) {
        System.out.println("Persistence listener reveived book added event for: " + event.getTitle() + " (" + event.getBookId() + ")");
        Book book = new Book();
        book.setBookId(event.getBookId());
        book.setTitle(event.getTitle());
        book.setReleaseDate(event.getReleaseDate());
        Author author = authorRepository.readByAuthorId(event.getAuthorId());
        book.setAuthor(author);
        bookRepository.create(book);
        System.out.println("Book entity created");
    }

    @EventHandler
    public void on(BookTitleChangedEvent event) {
        System.out.println("Persistence listener reveived book title changed event for: " + event.getNewTitle() + " (" + event.getBookId() + ")");
//        Book book = bookRepository.readByBookId(event.getBookId());
//        book.setTitle(event.getNewTitle());
//        bookRepository.update(book);
        System.out.println("Book entity updated");
    }

    @EventHandler
    public void on(BookRemovedEvent event) {
        System.out.println("Persistence listener reveived book removed event for: " + " (" + event.getBookId() + ")");
        Book book = bookRepository.readByBookId(event.getBookId());
        bookRepository.delete(book);
        System.out.println("Book entity deleted");
    }
}
