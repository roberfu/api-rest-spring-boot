package cl.springmachine.api.spring.boot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.spring.boot.jpa.entities.Edition;

public interface EditionRepository extends JpaRepository<Edition, Long> {

	List<Edition> findAllByBookId(Long bookId);
}
