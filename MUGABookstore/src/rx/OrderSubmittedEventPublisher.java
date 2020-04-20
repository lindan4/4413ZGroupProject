package rx;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmittedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderSubmittedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish() {
        OrderSubmittedEvent orderSubmittedEvent = new OrderSubmittedEvent(this);
        this.applicationEventPublisher.publishEvent(orderSubmittedEvent);
    }
}
