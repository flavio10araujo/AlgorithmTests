package ObjectedOrientedDesign.publicLibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraditionalBook extends Book {
	public String author;

	public TraditionalBook(String title, String author) {
		super(title);
		this.author = author;
		this.tags.add("traditional-book");
	}

	public static TraditionalBook parseDef(String bookRepresentation) {
		Pattern pattern = Pattern.compile("\"(.*)\" by (.*)");
		Matcher matcher = pattern.matcher(bookRepresentation);

		if (matcher.find()) {
			return new TraditionalBook(matcher.group(1), matcher.group(2));
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return String.format("\"%s\" by %s", title, author);
	}
}