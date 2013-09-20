package ch.bfh.swos.bookapp.vertx.controller;

import ch.bfh.swos.bookapp.cqrs.common.EventStorePlayer;
import ch.bfh.swos.bookapp.service.AuthorService;
import ch.bfh.swos.bookapp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 20.09.13
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/jpaviewcache")
public class ReplayController {

    @Inject
    private EventStorePlayer eventStorePlayer;

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    /**
     * Clean the Tables for the JPA ViewCache
     */
    @RequestMapping(value = "clean", method = RequestMethod.GET)
    @ResponseBody
    public void cleanViewJPACache() {
        System.out.println("Cleaning the JPA View Cache");
        bookService.deleteAll();
        authorService.deleteAll();
    }

    /**
     * Rebuild the Tables for the JPA ViewCache
     */
    @RequestMapping(value = "rebuild", method = RequestMethod.GET)
    @ResponseBody
    public void rebuildFromEventStore() {
        System.out.println("Rebuilding the JPA View Cache");
        eventStorePlayer.replay();
    }
}
