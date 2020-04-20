package rx;

import org.springframework.context.ApplicationEvent;

public class OrderSubmittedEvent extends ApplicationEvent {
    public OrderSubmittedEvent(Object source) {
        super(source);
    }
}
