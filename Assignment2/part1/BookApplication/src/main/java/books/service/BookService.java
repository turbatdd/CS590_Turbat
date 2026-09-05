package books.service;

import books.data.BookRepository;
import books.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {
    private static final String BOOK_QUEUE = "bookQueue";

    @Autowired
    BookRepository bookRepository;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BookService(BookRepository bookRepository, JmsTemplate jmsTemplate) {
        this.bookRepository = bookRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
        sendBook(book);
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book); // save acts as an update if the ID already exists
        sendBook(book);
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.getBook(isbn);
        bookRepository.deleteBook(isbn);
        if (book != null) {
            sendBook(book);
        }
    }

    public Book getBook(String isbn) {
        return bookRepository.getBook(isbn);
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    private void sendBook(Book book) {
        try {
            jmsTemplate.convertAndSend(BOOK_QUEUE, objectMapper.writeValueAsString(book));
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Could not serialize book for JMS", exception);
        }
    }
}
