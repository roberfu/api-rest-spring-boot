package cl.springmachine.api.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.library.entities.Edition;

public interface EditionRepository extends JpaRepository<Edition, Long> {

	List<Edition> findAllByBookId(Long bookId);
}
