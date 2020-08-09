package io.pillopl.testablearch.ex3.ui;

import io.pillopl.testablearch.ex3.DomainEventsPublisher;
import io.pillopl.testablearch.ex3.model.CardApplicationRejected;
import io.pillopl.testablearch.ex3.model.CardGranted;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyForCardWithEventMockBeanTest {

    @Autowired
    private CreditCardApplicationController creditCardApplicationController;

    /*
        the test with this will work successfully but if there no real bean say annotated with @Service of this impl then app will fail even though test will pass
        (the test is stupid, since the only thing we wanted to test has been mocked out (check docs of @MockBean there is a catch) :D)
     */
    @MockBean
    private DomainEventsPublisher domainEventsPublisher;

    @Test
    public void shouldEmitCardGrantedEvent() {
        // when
        creditCardApplicationController.applyForCard(new CardApplication("70232344"));

        // then (rabbitmq event should be launched , but we dont want our test to depend on some rabbitmq since it might fail in coming up and our test might fail to rather than running in isolation)
//          // maybe we can mock bean of type DomainEventPublisher
        Mockito.verify(domainEventsPublisher).publish(isA(CardGranted.class));
    }


    @Test
    public void shouldEmitCardApplicationRejectedEvent() {
        // when
        creditCardApplicationController.applyForCard(new CardApplication("50232344"));

        // then (rabbitmq event should be launched , but we dont want our test to depend on some rabbitmq since it might fail in coming up and our test might fail to rather than running in isolation)
//          // maybe we can mock bean of type DomainEventPublisher
        Mockito.verify(domainEventsPublisher).publish(isA(CardApplicationRejected.class));
    }
}
