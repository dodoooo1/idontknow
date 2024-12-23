package com.idontknow.business.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitApplicationStartupListener
        implements ApplicationListener<ApplicationReadyEvent> {

    private final RabbitListenerEndpointRegistry registry;

    /**
     * This event is executed as late as conceivably possible to indicate that the application is
     * ready to services requests.
     */
    @Override
    public void onApplicationEvent(final @NonNull ApplicationReadyEvent event) {
        this.registry.getListenerContainer(EventListener.RABBIT_ASYNC_EVENT_LISTENER_ID).start();
    }
}
