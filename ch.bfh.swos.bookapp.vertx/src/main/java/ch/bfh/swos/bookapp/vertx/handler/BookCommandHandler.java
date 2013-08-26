package ch.bfh.swos.bookapp.vertx.handler;

import ch.bfh.swos.bookapp.cqrs.book.application.command.AddBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.RemoveBookCommand;
import ch.bfh.swos.bookapp.vertx.VertXBean;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 26.08.13
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
@Component
@Named("vertxBookCommandHandler")
public class BookCommandHandler {

    public static final String COMMAND_ADD_BOOK = "command.add.book";
    public static final String COMMAND_REMOVE_BOOK = "command.remove.book";

    @Inject
    private VertXBean vertx;

    @Inject
    private CommandGateway commandGateway;

    public BookCommandHandler() {
    }

    @PostConstruct
    private void init() {
        vertx.getEventBus().registerHandler(COMMAND_ADD_BOOK, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(Message<JsonObject> payload) {
                System.out.println("VertX received book add command for: " + payload.body().getString("bookTitle"));
                payload.reply("Received book add command for: " + payload.body().getString("bookTitle"));
                commandGateway.send(new AddBookCommand(payload.body().getString("bookTitle"), new Date(payload.body().getLong("releaseDate")), payload.body().getString("authorId")));
            }
        });

        vertx.getEventBus().registerHandler(COMMAND_REMOVE_BOOK, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(Message<JsonObject> payload) {
                System.out.println("VertX received book remove command for: " + payload.body().getString("bookId"));
                payload.reply("Received book remove command for id: " + payload.body().getString("bookId"));
                commandGateway.send(new RemoveBookCommand(payload.body().getString("bookId")));
            }
        });
        System.out.println("VertX BookCommandHandler initialized");
    }
}
