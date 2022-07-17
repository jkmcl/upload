package jkml.web;

import java.io.IOException;
import java.util.Objects;

/**
 * Loosely based on the <a href=
 * "https://www.iana.org/assignments/media-types/text/tab-separated-values">IANA
 * definition of tab-separated-values</a>
 */
public class TsvBuilder {

	private static final char TAB = '\t';

	private static final char LF = '\n';

	private static final char CR = '\r';

	private final String lineSeparator;

	private final Appendable appendable;

	private boolean firstField = true;

	private TsvBuilder(String lineSeparator, Appendable appendable) {
		Objects.requireNonNull(lineSeparator);
		Objects.requireNonNull(appendable);
		this.lineSeparator = lineSeparator;
		this.appendable = appendable;
	}

	public static TsvBuilder system(Appendable appendable) {
		return new TsvBuilder(System.lineSeparator(), appendable);
	}

	public static TsvBuilder custom(String lineSeparator, Appendable appendable) {
		return new TsvBuilder(lineSeparator, appendable);
	}

	public TsvBuilder endFields() throws IOException {
		appendable.append(lineSeparator);
		firstField = true;
		return this;
	}

	public TsvBuilder addRecord(Iterable<String> fields) throws IOException {
		for (String f : fields) {
			addField(f);
		}
		endFields();
		return this;
	}

	public TsvBuilder addRecord(String... fields) throws IOException {
		for (String f : fields) {
			addField(f);
		}
		endFields();
		return this;
	}

	public TsvBuilder addField(String field) throws IOException {
		if (firstField) {
			firstField = false;
		} else {
			appendable.append(TAB);
		}

		if (field == null) {
			appendable.append("null");
		} else {
			for (int i = 0, n = field.length(); i < n; ++i) {
				append(field.charAt(i), appendable);
			}
		}

		return this;
	}

	private static void append(char c, Appendable appendable) throws IOException {
		if (c == TAB) {
			appendable.append("\\t");
		} else if (c == LF) {
			appendable.append("\\n");
		} else if (c == CR) {
			appendable.append("\\r");
		} else {
			appendable.append(c);
		}
	}

}
