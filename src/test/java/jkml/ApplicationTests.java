package jkml;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private Environment env;

	@Test
	void contextLoads() {
		assertNotNull(env);
	}

}
