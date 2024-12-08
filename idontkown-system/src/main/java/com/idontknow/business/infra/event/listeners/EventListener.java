package com.idontknow.business.infra.event.listeners;

import com.idontknow.business.infra.configs.rabbitmq.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    public static final String RABBIT_ASYNC_EVENT_LISTENER_ID = "EventListener";

    @Value("${rabbitmq.listeners.event.queue}")
    private String queueName;

    @RabbitListener(
            id = RABBIT_ASYNC_EVENT_LISTENER_ID,
            containerFactory = RabbitConfig.RABBIT_ASYNC_EVENT_LISTENER_FACTORY,
            queues = "${rabbitmq.listeners.event.queue}")
    public Mono<Void> process(final Message<?> message) {
        log.info("[RABBITMQ][SUB][{}] {}", this.queueName, message);
        return Mono.empty();
    }
}
