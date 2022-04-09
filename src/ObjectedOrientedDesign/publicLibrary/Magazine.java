package ObjectedOrientedDesign.publicLibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Magazine extends Book {
	public String issueNumber;

	public Magazine(String title, String issueNumber) {
		super(title);
		this.issueNumber = issueNumber;
		this.tags.add("magazine");
	}

	public static Magazine parseDef(String bookRepresentation) {
		Pattern pattern = Pattern.compile("\"(.*)\" Issue (.*)");
		Matcher matcher = pattern.matcher(bookRepresentation);
		if (matcher.find()) {
			return new Magazine(matcher.group(1), matcher.group(2));
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return String.format("\"%s\" Issue %s", title, issueNumber);
	}
}