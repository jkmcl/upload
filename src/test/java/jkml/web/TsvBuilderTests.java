package jkml.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TsvBuilderTests {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	void test() {
		TsvBuilder tb = new TsvBuilder(List.of("fileType", "baseName", "start", "stop"));
		tb.addRecord(List.of("master", "1234", "00:00:00", "23:59:59"));
		String tsv = tb.toString();
		assertNotNull(tsv);
		log.info(tsv);
	}

}
