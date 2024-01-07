package cl.springmachine.api.spring.boot.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.spring.boot.jpa.entities.Detail;

public interface DetailRepository extends JpaRepository<Detail, Long> {

}
