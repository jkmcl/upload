package jkml.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TsvBuilderTests {

	private static final Path OUT_DIR = Path.of("target/test-classes");

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	void test() throws IOException {
		Path filePath = OUT_DIR.resolve("tmp.tsv");

		TsvBuilder tb = new TsvBuilder(List.of("fileType", "baseName", "start", "stop"));
		tb.addRecord(List.of("master", "1234", "00:00:00", "23:59:59"));
		log.info(tb.toString());
		Files.writeString(filePath, tb.toString());
	}

}
