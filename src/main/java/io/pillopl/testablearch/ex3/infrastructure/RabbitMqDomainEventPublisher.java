package io.pillopl.testablearch.ex3.infrastructure;


import io.pillopl.testablearch.ex3.DomainEventsPublisher;
import io.pillopl.testablearch.ex3.model.DomainEvent;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jakub Pilimon
 */
@Primary
@Component
public class RabbitMqDomainEventPublisher implements DomainEventsPublisher {

    private final Source source;

    public RabbitMqDomainEventPublisher(Source source) {
        this.source = source;
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", domainEvent.getType());
        source.output().send(new GenericMessage<>(domainEvent, headers));
    }
}
