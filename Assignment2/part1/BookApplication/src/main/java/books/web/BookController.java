package books.web;

import books.domain.Book;
import books.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/{isbn}")
    public void updateBook(@PathVariable String isbn, @RequestBody Book book) {
        book.setIsbn(isbn);
        bookService.updateBook(book);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }

    @GetMapping("/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return bookService.getBook(isbn);
    }

    @GetMapping
    public Collection<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
