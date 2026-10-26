package bootcamp.hibernate_practical.repository;

import bootcamp.hibernate_practical.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthor(String author);

    List<Book> findByAvailableTrue();

    long count();

    List<Book> findBookByPublicationYearGreaterThan(int publicationYearIsGreaterThan);

    List<Book> findBooksByTitleContaining(String title);
}
