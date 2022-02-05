package jkml.web;

import java.time.Instant;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	private static final String TEXT_TSV_VALUE = "text/tab-separated-values;charset=UTF-8";

	@GetMapping("/now")
	public String now() {
		return Instant.now().toString();
	}

	@GetMapping(path = "/upload", produces = TEXT_TSV_VALUE)
	public String upload(@RequestParam String fileType, @RequestParam String baseName,
			@RequestParam(defaultValue = "true") boolean skipNames) {
		TsvBuilder tb = skipNames ? new TsvBuilder() : new TsvBuilder(List.of("fileType", "baseName", "startTime", "stopTime"));
		tb.addRecord(List.of(fileType, baseName, "21:00", "23:59"));
		return tb.toString();
	}

}
