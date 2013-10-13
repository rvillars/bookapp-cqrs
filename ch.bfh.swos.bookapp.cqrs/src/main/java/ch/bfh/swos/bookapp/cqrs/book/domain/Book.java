package ch.bfh.swos.bookapp.cqrs.book.domain;

import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookAddedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookRemovedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookTitleChangedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class Book extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String bookId;

    private String title;
    private Date releaseDate;

    private String authorId;

    public Book() {
    }

    public Book(String bookId, String title, Date releaseDate, String authorId) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        apply(new BookAddedEvent(bookId, title, releaseDate, authorId));
    }

    public void changeTitle(String newTitle) {
        // validation
        if (!newTitle.isEmpty()) {
            apply(new BookTitleChangedEvent(bookId, newTitle));
        }
    }

    public void remove() {
        apply(new BookRemovedEvent(bookId));
    }

    @EventHandler
    public void on(BookAddedEvent event) {
        System.out.println("BookAddedEvent called on Book");
        this.bookId = event.getBookId();
        this.title = event.getTitle();
        this.releaseDate = event.getReleaseDate();
        this.authorId = event.getAuthorId();
    }

    @EventHandler
    public void on(BookTitleChangedEvent event) {
        System.out.println("BookTitleChangedEvent called on Book");
        this.bookId = event.getBookId();
        this.title = event.getNewTitle();
    }

    @EventHandler
    public void on(BookRemovedEvent event) {
        System.out.println("BookRemovedEvent called on Book");
    }
}
