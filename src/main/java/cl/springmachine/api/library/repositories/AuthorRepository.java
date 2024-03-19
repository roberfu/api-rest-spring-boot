package cl.springmachine.api.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	List<Author> findAllByBooksId(Long id);
}
