package cl.springmachine.api.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.library.entities.Detail;

public interface DetailRepository extends JpaRepository<Detail, Long> {

}
