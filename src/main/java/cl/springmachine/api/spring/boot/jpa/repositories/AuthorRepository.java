package cl.springmachine.api.spring.boot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.spring.boot.jpa.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	List<Author> findAllByBooksId(Long id);
}
