package bootcamp.hibernate_practical.exception;

public class BookNotFoundByIdException extends RuntimeException {
    public BookNotFoundByIdException(Long id) {
        super("Book with id = " + id + " not found");
    }
}
