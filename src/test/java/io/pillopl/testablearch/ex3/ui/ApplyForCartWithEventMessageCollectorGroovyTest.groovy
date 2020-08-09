package io.pillopl.testablearch.ex3.ui

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Source
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.support.GenericMessage
import spock.lang.Specification

import java.util.concurrent.BlockingQueue

@SpringBootTest
class ApplyForCartWithEventMessageCollectorGroovyTest extends  Specification {

    @Autowired CreditCardApplicationController creditCardApplicationController

    @Autowired Source source

    @Autowired MessageCollector messageCollector

    BlockingQueue<GenericMessage<String>> events

    def setup() {
        events = messageCollector.forChannel(source.output());
        println "******* lets see if events is not empty : $events "
    }

    def 'should be able to get card when born in 70s or later'() {
        when:
            creditCardApplicationController.applyForCard(new CardApplication("70223432"))
        then:
            println "***** events present in queue : $events "
            events.poll().headers.containsValue("card-granted")
    }


}
