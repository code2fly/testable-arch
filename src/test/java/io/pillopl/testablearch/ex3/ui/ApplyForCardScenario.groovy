package io.pillopl.testablearch.ex3.ui

import io.pillopl.testablearch.ex3.application.ApplyForCardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest
class ApplyForCardScenario extends Specification {


    @Autowired(required = false)
    private CreditCardApplicationController creditCardApplicationController;


    def 'should be granted card if born in 70s or later'() {
        when:
        ResponseEntity response = creditCardApplicationController.applyForCard(new CardApplication("70345678"))
        then:
        response.statusCode.is2xxSuccessful()
    }

}
