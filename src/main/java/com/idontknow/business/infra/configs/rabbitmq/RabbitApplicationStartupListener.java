package com.idontknow.business.infra.configs.rabbitmq;

import com.idontknow.business.infra.event.listeners.EventListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RabbitApplicationStartupListener
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    RabbitListenerEndpointRegistry registry;

    /**
     * This event is executed as late as conceivably possible to indicate that the application is
     * ready to services requests.
     */
    @Override
    public void onApplicationEvent(final @NonNull ApplicationReadyEvent event) {
        this.registry.getListenerContainer(EventListener.RABBIT_ASYNC_EVENT_LISTENER_ID).start();
    }
}
