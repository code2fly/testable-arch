package io.pillopl.testablearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class TestableArchApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("hello");
	}

}
