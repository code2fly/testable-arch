package io.pillopl.testablearch.ex3.ui

import io.pillopl.testablearch.ex3.DomainEventsPublisher
import io.pillopl.testablearch.ex3.application.ApplyForCardService
import io.pillopl.testablearch.ex3.model.CardApplicationRejected
import io.pillopl.testablearch.ex3.model.CardGranted
import io.pillopl.testablearch.ex3.model.CreditCardRepository
import spock.lang.Specification

class ApplyForCardWithEventUnitTest extends Specification {

//    stub since we can specify what interactions to this should return
    CreditCardRepository creditCardRepository = Stub()
//    should be mock since we want to verify behavior on that, like what parameter this object method called with or how many times
    DomainEventsPublisher domainEventsPublisher = Mock()

    ApplyForCardService applyForCardService = new ApplyForCardService(domainEventsPublisher,creditCardRepository)

    def "should emit CardGranted when client born in 70s or later"() {
        when:
            applyForCardService.apply("70566633")
        then:
            1 * domainEventsPublisher.publish(_ as CardGranted)
    }


    def "should emit CardApplicationRejected when born before 70s" () {
        when:
            applyForCardService.apply("50234223")
        then:
            1 * domainEventsPublisher.publish(_ as CardApplicationRejected)
    }
}
