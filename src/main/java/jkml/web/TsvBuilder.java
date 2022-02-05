package jkml.web;

import java.util.List;

/**
 * Loosely based on the <a href=
 * "https://www.iana.org/assignments/media-types/text/tab-separated-values">IANA
 * definition of tab-separated-values</a>
 */
public class TsvBuilder {

	private static final String TAB = "\t";

	private static final String EOL = "\n";

	private final StringBuilder sb = new StringBuilder();

	public TsvBuilder() {
	}

	public TsvBuilder(List<String> names) {
		addRecord(names);
	}

	public TsvBuilder addRecord(List<String> fields) {
		sb.append(String.join(TAB, fields)).append(EOL);
		return this;
	}

	@Override
	public String toString() {
		return sb.toString();
	}

}
