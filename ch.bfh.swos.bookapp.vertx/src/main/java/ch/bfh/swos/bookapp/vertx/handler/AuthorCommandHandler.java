package ch.bfh.swos.bookapp.vertx.handler;

import ch.bfh.swos.bookapp.cqrs.author.application.command.AddAuthorCommand;
import ch.bfh.swos.bookapp.cqrs.author.application.command.RemoveAuthorCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.AddBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.RemoveBookCommand;
import ch.bfh.swos.bookapp.vertx.VertXBean;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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
@Named("vertxAuthorCommandHandler")
public class AuthorCommandHandler {

    public static final String COMMAND_ADD_AUTHOR = "command.add.author";
    public static final String COMMAND_REMOVE_AUTHOR = "command.remove.author";

    @Inject
    private VertXBean vertx;

    @Inject
    private CommandGateway commandGateway;

    @PostConstruct
    private void init() {
        vertx.getEventBus().registerHandler(COMMAND_ADD_AUTHOR, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(Message<JsonObject> payload) {
                System.out.println("VertX received author add command for: "+payload.body().getString("firstname")+ " " + payload.body().getString("lastname"));
                payload.reply("Received author add command for: "+payload.body().getString("firstname")+ " " + payload.body().getString("lastname"));
                commandGateway.send(new AddAuthorCommand(payload.body().getString("firstname"), payload.body().getString("lastname")));
            }
        });

        vertx.getEventBus().registerHandler(COMMAND_REMOVE_AUTHOR, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(Message<JsonObject> payload) {
                System.out.println("VertX received author remove command for: " + payload.body().getString("authorId"));
                payload.reply("Received author remove command for id: " + payload.body().getString("authorId"));
                commandGateway.send(new RemoveAuthorCommand(payload.body().getString("authorId")));
            }
        });
        System.out.println("VertX AuhtorCommandHandler initialized");
    }
}
