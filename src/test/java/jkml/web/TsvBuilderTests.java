package jkml.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TsvBuilderTests {

	private final Logger log = LoggerFactory.getLogger(TsvBuilderTests.class);

	@Test
	void test1() {
		TsvBuilder tb = TsvBuilder.system().addFields(List.of("fileType", "baseName", "start", "stop"));
		tb.addFields(List.of("master", "1234", "00:00:00", "23:59:59"));
		String tsv = tb.toString();
		assertNotNull(tsv);
		log.info(tsv);
	}

	@Test
	void test2() {
		TsvBuilder tb = TsvBuilder.custom("\n").addFields(List.of("a\t\r\n"));
		assertEquals("a\\t\\r\\n\n", tb.toString());
	}

	@Test
	void test3() {
		TsvBuilder tb0 = TsvBuilder.system();
		tb0.addFields("1a", "1b");
		tb0.addFields("2a", "2b");
		String tb0Out = tb0.toString();

		TsvBuilder tb1 = TsvBuilder.system();
		tb1.addFields(List.of("1a", "1b"));
		tb1.addFields(List.of("2a", "2b"));

		TsvBuilder tb2 = TsvBuilder.system();
		tb2.addField("1a").addField("1b").endFields();
		tb2.addField("2a").addField("2b").endFields();

		String tb1Out = tb1.toString();
		String tb2Out = tb2.toString();

		assertEquals(tb0Out, tb1Out);

		assertEquals(tb1Out, tb2Out);
	}

}
