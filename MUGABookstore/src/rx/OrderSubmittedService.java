package rx;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class OrderSubmittedService implements ApplicationListener<OrderSubmittedEvent> {
    private final MessageSendingOperations<String> messagingTemplate;

    public OrderSubmittedService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(OrderSubmittedEvent orderSubmittedEvent) {
        this.messagingTemplate.convertAndSend("/topic/order-submitted", "order-submitted");
    }
}
