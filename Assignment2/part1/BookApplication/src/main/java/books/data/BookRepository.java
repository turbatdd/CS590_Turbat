package books.data;

import books.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {
    private Map<String, Book> bookData = new HashMap<>();

    public void addBook(Book book) {
        bookData.put(book.getIsbn(), book);
    }

    public void updateBook(Book book) {
        bookData.put(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {
        bookData.remove(isbn);
    }

    public Book getBook(String isbn) {
        return bookData.get(isbn);
    }

    public Collection<Book> getAllBooks() {
        return bookData.values();
    }
}
