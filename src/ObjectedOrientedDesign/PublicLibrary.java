package ObjectedOrientedDesign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * In this question, we ask you to design a system for a public library that allows the looking up of books. 
 * You must design the system in a way that is easily expandable.
 * 
 * Part One
 * 
 * The library has a collection of books. 
 * All books has a title, which is usually a hint to their content. 
 * When a book is put in the system, it is also assigned a unique ID to identify the book, which consists of alphanumeric characters plus -.
 * 
 * Right now, there are two types of books in the library:
 * 
 * - Traditional books, where an author writes something and gets published. 
 *   They can be represented as "<book name>" by <author name>. 
 *   For example, "Macbeth" by W. Shakespeare (stretching a bit of the definition of a book here) or "The Lord of the Rings" by J. R. R. Tolkien. 
 *   For the purpose of this question, two authors are the same if and only if the string representing them are the same, 
 *   so we treat W. Shakespeare and William Shakespeare as different authors, even though they represent the same person.
 * - Magazines, where a group of people publishes a magazine series regularly. 
 *   They can be represented as "<magazine name>" Issue <issue number>. 
 *   For example, "New York Times" Issue 2.
 * 
 * For the first part, implement these following commands:
 * - register book [ID] [book]: 
 *   Register a traditional book under the ID. 
 *   If the ID is used by another book already, do nothing. 
 *   Multiple copies of the same book can be registered as different IDs.
 * - register magazine [ID] [book]: 
 *   Register a magazine under the ID, following the above rules.
 * - lookup id [ID]: 
 *   Print the book who is registered as ID, followed by ID: [ID] in the following line, if such ID exists in the system. 
 *   Otherwise, print "No such book exists".
 * - lookup title [book title]: 
 *   Look up a book by title (either a traditional book or a magazine). 
 *   If exactly one book in the system match the title, print the book in the first line, and print ID: [ID] in the second line. 
 *   If multiple books match the title, print "[number] books match the title: [title]", where number is the number of books that match the title. 
 *   If no book match the title, print "No such book exists".
 * - lookup author [author]: 
 *   Same as looking up by author, except look up a traditional book by author, and if multiple books exists, print "[number] books match the author: [author]" instead.
 * 
 * Input: instructions: A list of instructions.
 * Output: The list of strings as outputs.
 * 
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		'register book B-001 "Macbeth" by W. Shakespeare',
 * 		'register book B-002 "Hamlet" by W. Shakespeare',
 * 		'register book B-003 "The Lord of the Rings" by J. R. R. Tolkien',
 * 		'register magazine M-001 "New York Times" Issue 2',
 * 		'lookup id B-002',
 * 		'lookup title Macbeth',
 * 		'lookup author W. Shakespeare',
 * 		'lookup author J. R. R. Tolkien',
 * 		'lookup title New York Times'
 * ]
 * 
 * Output: [
 * 		'"Hamlet" by W. Shakespeare',
 * 		'ID: B-002',
 * 		'"Macbeth" by W. Shakespeare',
 * 		'ID: B-001',
 * 		'2 books match the author: W. Shakespeare',
 * 		'"The Lord of the Rings" by J. R. R. Tolkien',
 * 		'ID: B-003',
 * 		'"New York Times" Issue 2',
 * 		'ID: M-001'
 * ]
 * 
 * Solution
 * 
 * For this question, we define the base Book class, and two classes TraditionalBook and Magazine inheriting it. 
 * All books have a name, but traditional books have an author while magazines have an issue number. 
 * Define string representation for each class, as well as parsing the input into an object.
 * The Library class is there to keep track of the books in the library and provides a method that finds all books given a certain condition.
 * 
 * 
 * 
 * Part Two
 * 
 * What good is a library if people are not allowed to borrow books from it? 
 * For the next part, you need to implement a system for users to borrow books.
 * These are the following commands you need to implement:
 * - borrow [book ID] [name]: 
 *   The person with the name name attempts to borrow a book with that ID. 
 *   A person can only borrow exactly one book at a time. 
 *   If a person with the same name attempts to borrow a book while they have not returned any previous book they borrowed, do nothing. 
 *   If a book associated with that ID does not exist, or is already borrowed by someone else, do nothing.
 * - return [name]: 
 *   A person with the name name returns any book they have borrowed and have not returned yet.
 *   Additionally, there are some changes to be made to some previous commands. 
 *   When looking up books using the lookup command (by ID, title, or author), if exactly one book matches the criteria, 
 *   in addition to printing the book and the ID, if that book is borrowed by someone else already, 
 *   print "Borrowed by: [name]" in the next line, where name is the name of the person borrowing the book. 
 *   If multiple books match the criteria, in addition to print the number of books that match, 
 *   also print "[number] book(s) available" in the next line, where number is the number of books that can be borrowed by people.
 * 
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		'register book B-001 "Macbeth" by W. Shakespeare',
 * 		'register book B-002 "Hamlet" by W. Shakespeare',
 * 		'register magazine M-001 "New York Times" Issue 2',
 * 		'borrow B-002 John Smith',
 * 		'lookup id B-002',
 * 		'borrow M-001 John Smith',
 * 		'borrow M-001 Jane Doe',
 * 		'lookup title New York Times',
 * 		'lookup author W. Shakespeare',
 * 		'return John Smith',
 * 		'lookup title Hamlet'
 * ]
 * 
 * Output: [
 * 		'"Hamlet" by W. Shakespeare',
 * 		'ID: B-002',
 * 		'Borrowed by: John Smith',
 * 		'"New York Times" Issue 2',
 * 		'ID: M-001',
 * 		'Borrowed by: Jane Doe',
 * 		'2 books match the author: W. Shakespeare',
 * 		'1 book(s) available',
 * 		'"Hamlet" by W. Shakespeare',
 * 		'ID: B-002'
 * ]
 * 
 * Solution
 * 
 * For this part, we implement the User class. 
 * Each user is uniquely identified by name, and each user can have a book borrowed. 
 * Additionally, each book can be borrowed by one user.
 * The library now also keep track of a list of users, and output books depending on which ones are borrowed.
 * 
 * 
 * 
 * Part Three
 * 
 * Users complain that the system only gives the title of the book, and a lot of book titles are not indicative of their content. 
 * As a solution, the library introduces a "tag" system.
 * Each book (either traditional or magazine) can have several tags associated with them (a "tag" may consist of case-sensitive alphanumeric characters plus -), and the system allows the look up of books by tags.
 * Additionally, user can favorite tags that they like and have books recommended to them.
 * 
 * Here are the additional commands that you need to implement:
 * - tag [book ID] [tags...]: 
 *   Tag the book by ID with a list of tags, separated by space.
 * - favorite [tag] [name]: 
 *   List the tag as a favorite of a user.
 * - lookup tags [tags...]: 
 *   Same as looking up books by title, except we look for books that has all the tags listed, and if multiple books match, print "[number] books match the tag(s): [tags...]" instead on the first line.
 * - lookup suggestion [name]: 
 *   Suggest books based on a user's favorite tags. 
 *   To do that, look up the book whose tags appears the most in that user's favorite tags. 
 *   If exactly one book has the most favorite tags, print it according to the rules of looking up the book by ID. 
 *   If multiple books have the most favorite tags, print "[number] books are suggested for: [name]", followed by the number of books available to borrow like in part two. 
 *   If none of the books have at least one tags that matches the user's favorite tags, print "No such book exists".
 *   
 * Note: 
 * Traditional books always has the traditional-book tag and magazines always has the magazine tag, and they each cannot gain the other tag. 
 * Do nothing if a book attempts to add an incorrect tag (other tags are still added, if able). 
 * A book can also only have up to one of each unique tags, and a user can only favorite up to one of each unique tags.
 * 
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		'register book B-001 "Macbeth" by W. Shakespeare',
 * 		'register book B-002 "Hamlet" by W. Shakespeare',
 * 		'register magazine M-001 "New York Times" Issue 2',
 * 		'tag B-001 tragedy play grade-12',
 * 		'tag B-002 tragedy play grade-11',
 * 		'tag M-001 news grade-11',
 * 		'lookup tags play tragedy',
 * 		'favorite traditional-book John Smith',
 * 		'favorite grade-11 John Smith',
 * 		'lookup suggestion John Smith'
 * ]
 * 
 * Output: [
 * 		'2 books match the tag(s): play tragedy',
 * 		'2 book(s) available',
 * 		'"Hamlet" by W. Shakespeare',
 * 		'ID: B-002'
 * ]
 * 
 * Solution
 * 
 * For this part, each book has a set of tags and each user has a set of favorite tags. 
 * The only thing to be careful is that TraditionalBook has the traditional-book tag while Magazine has the magazine tag, and they each cannot gain the other.
 */
public class PublicLibrary {

	public static abstract class Book {
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

	public static class TraditionalBook extends Book {
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

	public static class Magazine extends Book {
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

	public static class User {
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

	public static class Library {
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

	public static void outputBooks(List<Book> bookList, List<String> output, Function<List<Book>, String> multipleMatchOutput) {
		if (bookList.isEmpty()) {
			output.add("No such book exists");
		} else if (bookList.size() == 1) {
			output.add(bookList.get(0).toString());
			output.add(String.format("ID: %s", bookList.get(0).id));

			if (bookList.get(0).borrowedBy != null) {
				output.add(String.format("Borrowed by: %s", bookList.get(0).borrowedBy.toString()));
			}
		} else {
			if (multipleMatchOutput == null) {
				return;
			}

			output.add(multipleMatchOutput.apply(bookList));
			int availableCount = 0;

			for (Book book : bookList) {
				if (book.borrowedBy == null) {
					availableCount++;
				}
			}

			output.add(String.format("%d book(s) available", availableCount));
		}
	}

	public static List<String> simulateLibrary(List<String> instructions) {
		Library library = new Library();
		List<String> output = new ArrayList<>();

		for (String instruction : instructions) {
			String[] splitResult = instruction.split(" ", 2);

			if (splitResult[0].equals("register")) {
				splitResult = splitResult[1].split(" ", 3);

				Book newBook = null;

				if (splitResult[0].equals("book")) {
					newBook = TraditionalBook.parseDef(splitResult[2]);
				} else if (splitResult[0].equals("magazine")) {
					newBook = Magazine.parseDef(splitResult[2]);
				}

				if (newBook != null) {
					newBook.id = splitResult[1];
					library.registerBook(newBook);
				}
			}
			else if (splitResult[0].equals("lookup")) {
				splitResult = splitResult[1].split(" ", 2);
				final String lookupParameter = splitResult[1];

				if (splitResult[0].equals("id")) {
					List<Book> bookList = library.lookupBooks((book) -> book.id.equals(lookupParameter));
					outputBooks(bookList, output, null);
				} else if (splitResult[0].equals("title")) {
					List<Book> bookList = library.lookupBooks((book) -> book.title.equals(lookupParameter));
					outputBooks(bookList, output, (outputBookList) -> String.format("%d books match the title: %s", outputBookList.size(), lookupParameter));
				} else if (splitResult[0].equals("author")) {
					List<Book> bookList = library.lookupBooks((book) -> (book instanceof TraditionalBook) && ((TraditionalBook)(book)).author.equals(lookupParameter));
					outputBooks(bookList, output, (outputBookList) -> String.format("%d books match the author: %s", outputBookList.size(), lookupParameter));
				} else if (splitResult[0].equals("tags")) {
					String[] tags = lookupParameter.split(" ");

					List<Book> bookList = library.lookupBooks((book) -> {
						for (String tag : tags) {
							if (!book.tags.contains(tag)) {
								return false;
							}
						}

						return true;
					});

					outputBooks(bookList, output, (outputBookList) -> String.format("%d books match the tag(s): %s", outputBookList.size(), lookupParameter));
				} else if (splitResult[0].equals("suggestion")) {
					User user = library.findUser(lookupParameter);
					int maxScore = user.getMaxSuggestionScore(library.books.values());
					List<Book> bookList = library.lookupBooks((book) -> user.getSuggestionScore(book) > 0 && user.getSuggestionScore(book) == maxScore);
					outputBooks(bookList, output, (outputBookList) -> String.format("%d books are suggested for: %s", outputBookList.size(), lookupParameter));
				}
			} else if (splitResult[0].equals("borrow")) {
				splitResult = splitResult[1].split(" ", 2);

				if (library.books.containsKey(splitResult[0])) {
					library.findUser(splitResult[1]).borrowBook(library.books.get(splitResult[0]));
				}
			} else if (splitResult[0].equals("return")) {
				library.findUser(splitResult[1]).returnBook();
			} else if (splitResult[0].equals("tag")) {
				splitResult = splitResult[1].split(" ");

				if (library.books.containsKey(splitResult[0])) {
					Book book = library.books.get(splitResult[0]);

					for (int i = 1; i < splitResult.length; i++) {
						book.addTag(splitResult[i]);
					}
				}
			} else if (splitResult[0].equals("favorite")) {
				splitResult = splitResult[1].split(" ", 2);
				library.findUser(splitResult[1]).addFavoriteTag(splitResult[0]);
			}
		}

		return output;
	}

	/*
     9
     register book B-001 "Macbeth" by W. Shakespeare
  	 register book B-002 "Hamlet" by W. Shakespeare
     register book B-003 "The Lord of the Rings" by J. R. R. Tolkien
     register magazine M-001 "New York Times" Issue 2
     lookup id B-002
     lookup title Macbeth
     lookup author W. Shakespeare
     lookup author J. R. R. Tolkien
     lookup title New York Times
	 */
	// Testing Part One.
	/*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int instructionsLength = Integer.parseInt(scanner.nextLine());
        List<String> instructions = new ArrayList<>();

        for (int i = 0; i < instructionsLength; i++) {
            instructions.add(scanner.nextLine());
        }

        scanner.close();

        List<String> res = simulateLibrary(instructions);

        for (String line : res) {
            System.out.println(line);
        }
    }*/

	/*
	 11
	 register book B-001 "Macbeth" by W. Shakespeare
     register book B-002 "Hamlet" by W. Shakespeare
     register magazine M-001 "New York Times" Issue 2
     borrow B-002 John Smith
     lookup id B-002
     borrow M-001 John Smith
     borrow M-001 Jane Doe
     lookup title New York Times
     lookup author W. Shakespeare
     return John Smith
     lookup title Hamlet
	 */
	// Testing Part Two.
	/*public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int instructionsLength = Integer.parseInt(scanner.nextLine());
		List<String> instructions = new ArrayList<>();

		for (int i = 0; i < instructionsLength; i++) {
			instructions.add(scanner.nextLine());
		}

		scanner.close();
		List<String> res = simulateLibrary(instructions);

		for (String line : res) {
			System.out.println(line);
		}
	}*/

	/*
	 10
	 register book B-001 "Macbeth" by W. Shakespeare
     register book B-002 "Hamlet" by W. Shakespeare
     register magazine M-001 "New York Times" Issue 2
     tag B-001 tragedy play grade-12
     tag B-002 tragedy play grade-11
     tag M-001 news grade-11
     lookup tags play tragedy
     favorite traditional-book John Smith
     favorite grade-11 John Smith
     lookup suggestion John Smith
	 */
	// Testing Part Three.
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int instructionsLength = Integer.parseInt(scanner.nextLine());
		List<String> instructions = new ArrayList<>();
		
		for (int i = 0; i < instructionsLength; i++) {
			instructions.add(scanner.nextLine());
		}
		
		scanner.close();
		List<String> res = simulateLibrary(instructions);
		
		for (String line : res) {
			System.out.println(line);
		}
	}
}