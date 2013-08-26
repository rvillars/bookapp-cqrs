package ch.bfh.swos.bookapp.cqrs.author.application.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.axonframework.domain.IdentifierFactory;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 13.08.13
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class AddAuthorCommand {

    @TargetAggregateIdentifier
    private final String authorId;
    private final String firstname;
    private final String lastname;

    public AddAuthorCommand(String firstname, String lastname) {
        this.authorId = IdentifierFactory.getInstance().generateIdentifier();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public AddAuthorCommand(String authorId, String firstname, String lastname) {
        this.authorId = authorId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
