package jkml.web;

import java.util.Collection;

/**
 * Loosely based on the <a href=
 * "https://www.iana.org/assignments/media-types/text/tab-separated-values">IANA
 * definition of tab-separated-values</a>
 */
public class TsvBuilder {

	private static final char TAB = '\t';

	private static final char LF = '\n';

	private static final char CR = '\r';

	private final StringBuilder sb = new StringBuilder();

	private final String lineSeparator;

	private boolean firstField = true;

	private TsvBuilder(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	public static TsvBuilder system() {
		return new TsvBuilder(System.lineSeparator());
	}

	public static TsvBuilder custom(String lineSeparator) {
		return new TsvBuilder(lineSeparator);
	}

	public void clear() {
		sb.setLength(0);
		firstField = true;
	}

	@Override
	public String toString() {
		return sb.toString();
	}

	public TsvBuilder endFields() {
		sb.append(lineSeparator);
		firstField = true;
		return this;
	}

	public TsvBuilder addFields(String... fields) {
		for (String f : fields) {
			addField(f);
		}
		endFields();
		return this;
	}

	public TsvBuilder addFields(Collection<String> fields) {
		for (String f : fields) {
			addField(f);
		}
		endFields();
		return this;
	}

	public TsvBuilder addField(String field) {
		if (firstField) {
			firstField = false;
		} else {
			sb.append(TAB);
		}

		if (field == null) {
			sb.append("null");
		} else {
			for (int i = 0, n = field.length(); i < n; ++i) {
				append(field.charAt(i), sb);
			}
		}

		return this;
	}

	private static void append(char c, StringBuilder sb) {
		if (c == TAB) {
			sb.append("\\t");
		} else if (c == LF) {
			sb.append("\\n");
		} else if (c == CR) {
			sb.append("\\r");
		} else {
			sb.append(c);
		}
	}

}
