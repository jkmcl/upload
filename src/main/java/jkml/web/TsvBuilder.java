package jkml.web;

import java.io.IOException;
import java.util.Objects;

/**
 * Loosely based on the <a href=
 * "https://www.iana.org/assignments/media-types/text/tab-separated-values">IANA
 * definition of tab-separated-values</a>
 */
public class TsvBuilder {

	private static final char HT = '\t';

	private static final char LF = '\n';

	private static final char CR = '\r';

	private static final char BS = '\\'; // backslash, not backspace

	private final String lineSeparator;

	private final Appendable output;

	private boolean firstField = true;

	private TsvBuilder(String lineSeparator, Appendable output) {
		this.lineSeparator = Objects.requireNonNull(lineSeparator);
		this.output = Objects.requireNonNull(output);
	}

	public static TsvBuilder system(Appendable output) {
		return new TsvBuilder(System.lineSeparator(), output);
	}

	public static TsvBuilder windows(Appendable output) {
		return new TsvBuilder("\r\n", output);
	}

	public static TsvBuilder unix(Appendable output) {
		return new TsvBuilder("\n", output);
	}

	public TsvBuilder addField(String field) throws IOException {
		if (firstField) {
			firstField = false;
		} else {
			output.append(HT);
		}

		if (field == null) {
			output.append("null");
			return this;
		}

		for (int i = 0, n = field.length(); i < n; ++i) {
			char c = field.charAt(i);
			if (c == HT) {
				output.append("\\t");
			} else if (c == LF) {
				output.append("\\n");
			} else if (c == CR) {
				output.append("\\r");
			} else if (c == BS) {
				output.append("\\\\");
			} else {
				output.append(c);
			}
		}
		return this;
	}

	public TsvBuilder endFields() throws IOException {
		output.append(lineSeparator);
		firstField = true;
		return this;
	}

	public TsvBuilder addRecord(String... fields) throws IOException {
		for (String f : fields) {
			addField(f);
		}
		endFields();
		return this;
	}

	public TsvBuilder addRecord(Iterable<String> fields) throws IOException {
		for (String f : fields) {
			addField(f);
		}
		endFields();
		return this;
	}

}
