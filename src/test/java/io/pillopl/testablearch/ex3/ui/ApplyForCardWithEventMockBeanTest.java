package io.pillopl.testablearch.ex3.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyForCardWithEventMockBeanTest {

    @Autowired
    private CreditCardApplicationController creditCardApplicationController;

    @Test
    public void shouldEmitCardGrantedEvent() {
        // when
        creditCardApplicationController.applyForCard(new CardApplication("70232344"));

        // then (rabbitmq event should be launched , but we dont want our test to depend on some rabbitmq since it might fail in coming up and our test might fail to rather than running in isolation)
//          // maybe we can mock bean of type DomainEventPublisher

    }


    @Test
    public void shouldEmitCardApplicationRejectedEvent() {

    }
}
