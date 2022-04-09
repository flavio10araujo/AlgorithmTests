package ObjectedOrientedDesign.publicLibrary;

import java.util.HashSet;
import java.util.Set;

public abstract class Book {

	public String title;
	public String id;
	public User borrowedBy;
	public Set<String> tags;

	public static final Set<String> disallowedTags = Set.of("traditional-book", "magazine");

	public Book(String title) {
		this.title = title;
		this.id = null;
		this.borrowedBy = null;
		this.tags = new HashSet<>();
	}

	public void addTag(String tag) {
		if (!disallowedTags.contains(tag)) {
			tags.add(tag);
		}
	}
}