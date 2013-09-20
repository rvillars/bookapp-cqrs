package ch.bfh.swos.bookapp.cqrs.common;

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventstore.EventVisitor;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 20.09.13
 * Time: 09:09
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EventStorePlayer {

    @Inject
    @Named("eventStore")
    private JpaEventStore eventStore;

    @Inject
    @Named("eventBus")
    private EventBus eventBus;

    public void replay() {
        eventStore.visitEvents(new EventVisitor() {
            @Override
            public void doWithEvent(DomainEventMessage domainEvent) {
                eventBus.publish(domainEvent);
            }
        });
    }
}
