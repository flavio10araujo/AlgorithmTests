package ObjectedOrientedDesign.publicLibrary;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User {

	public String name;
	public Book borrowedBook;
	public Set<String> favoriteTags;

	public User(String name) {
		this.name = name;
		this.borrowedBook = null;
		this.favoriteTags = new HashSet<>();
	}

	@Override
	public String toString() {
		return name;
	}

	public void borrowBook(Book book) {
		if (borrowedBook == null && book.borrowedBy == null) {
			borrowedBook = book;
			book.borrowedBy = this;
		}
	}

	public void returnBook() {
		if (borrowedBook != null && borrowedBook.borrowedBy != null) {
			borrowedBook.borrowedBy = null;
			borrowedBook = null;
		}
	}

	public void addFavoriteTag(String tag) {
		favoriteTags.add(tag);
	}

	public int getSuggestionScore(Book book) {
		int count = 0;

		for (String tag : book.tags) {
			if (favoriteTags.contains(tag)) {
				count++;
			}
		}

		return count;
	}

	public int getMaxSuggestionScore(Collection<Book> books) {
		int max = 0;

		for (Book book : books) {
			max = Math.max(max, getSuggestionScore(book));
		}

		return max;
	}
}