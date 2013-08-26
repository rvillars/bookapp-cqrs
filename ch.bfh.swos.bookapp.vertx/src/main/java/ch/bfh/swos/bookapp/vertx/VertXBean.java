package ch.bfh.swos.bookapp.vertx;

import ch.bfh.swos.bookapp.cqrs.book.application.command.AddBookCommand;
import ch.bfh.swos.bookapp.cqrs.book.application.command.RemoveBookCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.SockJSServer;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 21.08.13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
@Service
public class VertXBean {

    private final EventBus eventBus;

    private final Vertx vertx;

    public VertXBean() {
        super();
        vertx = VertxFactory.newVertx();
        start();
        eventBus = vertx.eventBus();
    }

    public void start() {

        RouteMatcher routeMatcher = new RouteMatcher();

        // HTTP server
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(routeMatcher);

        // SockJS server
        JsonArray permitted = new JsonArray();
        permitted.add(new JsonObject()); // Let everything through
        SockJSServer sockJSServer = vertx.createSockJSServer(httpServer);
        sockJSServer.bridge(new JsonObject().putString("prefix", "/eventbus"), permitted, permitted);

        httpServer.listen(7777);

        System.out.println("Vert.X Core UP");
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
