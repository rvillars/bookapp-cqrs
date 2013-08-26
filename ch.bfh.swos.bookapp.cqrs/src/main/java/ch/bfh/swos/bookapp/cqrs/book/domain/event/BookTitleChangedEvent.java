package ch.bfh.swos.bookapp.cqrs.book.domain.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class BookTitleChangedEvent {

    @TargetAggregateIdentifier
    private final String bookId;
    private final String newTitle;


    public BookTitleChangedEvent(String bookId, String newTitle) {
        this.bookId = bookId;
        this.newTitle = newTitle;
    }

    public String getBookId() {
        return bookId;
    }

    public String getNewTitle() {
        return newTitle;
    }
}
