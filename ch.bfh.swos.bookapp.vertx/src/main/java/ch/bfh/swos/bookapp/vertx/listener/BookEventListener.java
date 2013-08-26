package ch.bfh.swos.bookapp.vertx.listener;

import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookAddedEvent;
import ch.bfh.swos.bookapp.cqrs.book.domain.event.BookRemovedEvent;
import ch.bfh.swos.bookapp.vertx.VertXBean;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 12.08.13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BookEventListener {

    public static final String EVENT_BOOK_ADDED = "event.book.added";
    public static final String EVENT_BOOK_REMOVED = "event.book.removed";

    @Inject
    private VertXBean vertxBean;

    @EventHandler
    public void on(BookAddedEvent event) {
        System.out.println("VertX listener reveived book added event for: " + event.getTitle() + " (" + event.getBookId() + ")");
        JsonObject paylod = new JsonObject();
        paylod.putString("bookId", event.getBookId());
        paylod.putString("bookTitle", event.getTitle());
        paylod.putNumber("releaseDate", event.getReleaseDate().getTime());
        paylod.putString("authorId", event.getAuthorId());
        vertxBean.getEventBus().publish(EVENT_BOOK_ADDED, paylod);
    }

    @EventHandler
    public void on(BookRemovedEvent event) {
        System.out.println("VertX listener reveived book removed event for: " + event.getBookId());
        JsonObject paylod = new JsonObject();
        paylod.putString("bookId", event.getBookId());
        vertxBean.getEventBus().publish(EVENT_BOOK_REMOVED,paylod);
    }
}
