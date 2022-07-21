package jkml.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.StringWriter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TsvBuilderTests {

	private final Logger log = LoggerFactory.getLogger(TsvBuilderTests.class);

	@Test
	void test1() throws Exception {
		StringBuilder sb = new StringBuilder();
		TsvBuilder tb = TsvBuilder.system(sb).addRecord(List.of("fileType", "baseName", "start", "stop"));
		tb.addRecord(List.of("master", "1234", "00:00:00", "23:59:59"));
		String tsv = sb.toString();
		assertNotNull(tsv);
		log.info(tsv);
	}

	@Test
	void test2() throws Exception {
		StringBuilder sb = new StringBuilder();
		TsvBuilder.unix(sb).addRecord(List.of("\\a\t\r\n"));
		assertEquals("\\\\a\\t\\r\\n\n", sb.toString());
	}

	@Test
	void test3() throws Exception {
		StringBuilder sb = new StringBuilder();

		TsvBuilder tb0 = TsvBuilder.system(sb);
		tb0.addRecord("1a", "1b");
		tb0.addRecord("2a", "2b");
		String tsv0 = sb.toString();

		sb.setLength(0);
		TsvBuilder tb1 = TsvBuilder.system(sb);
		tb1.addRecord(List.of("1a", "1b"));
		tb1.addRecord(List.of("2a", "2b"));
		String tsv1 = sb.toString();

		assertEquals(tsv0, tsv1);

		sb.setLength(0);
		TsvBuilder tb2 = TsvBuilder.system(sb);
		tb2.addField("1a").addField("1b").endFields();
		tb2.addField("2a").addField("2b").endFields();
		String tsv2 = sb.toString();

		assertEquals(tsv1, tsv2);
	}

	@Test
	void test4() throws Exception {
		StringWriter sw = new StringWriter();
		TsvBuilder.unix(sw).addRecord("hello", "world", null);
		assertEquals("hello\tworld\tnull\n", sw.toString());
	}

}
