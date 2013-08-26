package ch.bfh.swos.bookapp.cqrs.author.application.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class RemoveAuthorCommand {

    @TargetAggregateIdentifier
    private final String authorId;

    public RemoveAuthorCommand(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }
}
