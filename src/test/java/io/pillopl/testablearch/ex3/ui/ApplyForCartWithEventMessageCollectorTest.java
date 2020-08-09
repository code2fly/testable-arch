package io.pillopl.testablearch.ex3.ui;

import io.pillopl.testablearch.ex3.MessagingApplication;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = MessagingApplication.class)
public class ApplyForCartWithEventMessageCollectorTest {

    @Autowired
    CreditCardApplicationController cardApplicationController;

    @Autowired
    Source source;

    @Autowired
    MessageCollector messageCollector;

    // this is for kafka/mq custom impl for testing
    BlockingQueue<Message<?>> events;

    @BeforeEach
    public void setup() {
        events = messageCollector.forChannel(source.output());
    }

    @Test
    public void shouldGetCardWhenBornIn70OrLater() {
//        cardApplicationController.applyForCard(new CardApplication("70345678"));
        cardApplicationController.applyForCard(new CardApplication("70345678"));
        Message<?> message = events.poll();
//
        assertTrue(message.getHeaders().containsValue("card-granted"));
    }

    @Test
    public void shouldNotGetCardWhenBornBefore70s() {
        cardApplicationController.applyForCard(new CardApplication("56909238"));
        Message<?> msg = events.poll();

        assertTrue(msg.getHeaders().containsValue("card-application-rejected"));
    }


}
