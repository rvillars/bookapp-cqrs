package ch.bfh.swos.bookapp.cqrs.book.domain.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class BookRemovedEvent {

    @TargetAggregateIdentifier
    private final String bookId;

    public BookRemovedEvent(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
