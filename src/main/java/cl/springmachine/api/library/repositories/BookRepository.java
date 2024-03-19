package cl.springmachine.api.library.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findAllByAuthorsId(Long authorId);

	Optional<Book> findOneByEditionsId(Long editionId);

}
