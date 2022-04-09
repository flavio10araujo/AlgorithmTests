package ObjectedOrientedDesign.publicLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Library {

	public Map<String, Book> books;
	public Map<String, User> users;

	public Library() {
		books = new HashMap<>();
		users = new HashMap<>();
	}

	public void registerBook(Book book) {
		if (!books.containsKey(book.id)) {
			books.put(book.id, book);
		}
	}

	public List<Book> lookupBooks(Predicate<Book> lookupFn) {
		List<Book> result = new ArrayList<>();

		for (Book book : books.values()) {
			if (lookupFn.test(book)) {
				result.add(book);
			}
		}

		return result;
	}

	public User findUser(String name) {
		if (!users.containsKey(name)) {
			users.put(name, new User(name));
		}
		return users.get(name);
	}
}