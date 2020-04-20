package rx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderSubmittedService implements ApplicationListener<OrderSubmittedEvent> {
    @Autowired
    private SimpMessagingTemplate webSocket;

    @Override
    public void onApplicationEvent(OrderSubmittedEvent orderSubmittedEvent) {
        this.webSocket.convertAndSend("/order-submitted", "order-submitted");
    }
}
