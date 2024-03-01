package BookShelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookShelf {

    private List<String> books = new ArrayList<>();

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public int numberOfBooks() {
        return books.size();
    }

    public void addBooks(String... bookTitles) {
        Collections.addAll(books, bookTitles);
    }

    public List<String> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void arrangeByTitle() {
        Collections.sort(books);
    }

    public List<String> getInsertionOrder() {
        return new ArrayList<>(books);
    }


public static void main(String[] args) {
    // Create a BookShelf instance
    BookShelf bookShelf = new BookShelf();

    // Add books to the shelf
    bookShelf.addBooks("Book 1", "Book 2", "Book 3");

    // Arrange the books by title
    bookShelf.arrangeByTitle();

    // Retrieve the list of books
    List<String> books = bookShelf.getBooks();

    // Display the books
    System.out.println("Books on the shelf:");
    for (String book : books) {
        System.out.println(book);
    }

    // Get the insertion order of books
    List<String> insertionOrder = bookShelf.getInsertionOrder();
    System.out.println("\nInsertion order of books:");
    for (String book : insertionOrder) {
        System.out.println(book);
    }
}
}
