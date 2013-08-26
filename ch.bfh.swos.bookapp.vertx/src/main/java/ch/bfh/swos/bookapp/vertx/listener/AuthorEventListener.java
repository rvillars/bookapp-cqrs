package ch.bfh.swos.bookapp.vertx.listener;

import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorAddedEvent;
import ch.bfh.swos.bookapp.cqrs.author.domain.event.AuthorRemovedEvent;
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
public class AuthorEventListener {

    public static final String EVENT_AUTHOR_ADDED = "event.author.added";
    public static final String EVENT_AUTHOR_REMOVED = "event.author.removed";

    @Inject
    private VertXBean vertxBean;

    @EventHandler
    public void on(AuthorAddedEvent event) {
        System.out.println("VertX listener reveived author added event for: " + event.getFirstname() + " " +event.getLastname() + " (" + event.getAuthorId() + ")");
        JsonObject paylod = new JsonObject();
        paylod.putString("authorId", event.getAuthorId());
        paylod.putString("firstname", event.getFirstname());
        paylod.putString("lastname", event.getLastname());
        vertxBean.getEventBus().publish(EVENT_AUTHOR_ADDED, paylod);
    }

    @EventHandler
    public void on(AuthorRemovedEvent event) {
        System.out.println("VertX listener reveived author removed event for: " + event.getAuthorId());
        JsonObject paylod = new JsonObject();
        paylod.putString("authorId", event.getAuthorId());
        vertxBean.getEventBus().publish(EVENT_AUTHOR_REMOVED,paylod);
    }
}
