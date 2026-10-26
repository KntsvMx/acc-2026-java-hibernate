package bootcamp.hibernate_practical.service;

import bootcamp.hibernate_practical.dto.BookResponse;
import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.dto.UpdateBookRequest;
import bootcamp.hibernate_practical.entity.Book;
import bootcamp.hibernate_practical.exception.BookNotFoundByIdException;
import bootcamp.hibernate_practical.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(CreateBookRequest request) {
        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getGenre(),
                request.getPublicationYear(),
                true
        );
        Book savedBook = bookRepository.save(book);
        return mapToResponse(savedBook);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id).map(this::mapToResponse).orElseThrow(() -> new BookNotFoundByIdException(id));
    }

    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundByIdException(id));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setPublicationYear(request.getPublicationYear());
        book.setAvailable(request.isAvailable());

        bookRepository.save(book);

        return mapToResponse(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookResponse> findByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author).stream().map(this::mapToResponse).toList();
    }

    public List<BookResponse> findAvailableBooks() {
        return bookRepository.findByAvailableTrue().stream().map(this::mapToResponse).toList();
    }

    public Integer countBooks() {
        return (int) bookRepository.count();
    }

    public List<BookResponse> findBooksAfterGivenYear(int year) {
        return bookRepository.findBookByPublicationYearGreaterThan(year).stream().map(this::mapToResponse).toList();
    }

    public List<BookResponse> findBookByPartTitle(String title) {
        return bookRepository.findBooksByTitleContaining(title).stream().map(this::mapToResponse).toList();
    }

    private BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .publicationYear(book.getPublicationYear())
                .available(book.isAvailable())
                .build();
    }
}
