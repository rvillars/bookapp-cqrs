package ch.bfh.swos.bookapp.cqrs.book.domain.event;

import ch.bfh.swos.bookapp.cqrs.author.domain.Author;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class BookAddedEvent {

    @TargetAggregateIdentifier
    private final String bookId;
    private final String title;
    private final Date releaseDate;
    private final String authorId;


    public BookAddedEvent(String bookId, String title, Date releaseDate, String authorId) {
        this.bookId = bookId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.authorId = authorId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getAuthorId() {
        return authorId;
    }
}
