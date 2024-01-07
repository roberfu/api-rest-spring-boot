package cl.springmachine.api.spring.boot.jpa.services;

import java.util.List;

import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.DetailDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionBookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;

public interface EditionService {

	List<EditionResponseDto> getAllEditions();

	EditionBookResponseDto getEditionById(Long editionId);

	EditionBookResponseDto saveEdition(EditionRequestDto request) throws CustomException;

	EditionBookResponseDto updateEdition(EditionRequestDto request, Long editionId) throws CustomException;

	void deleteEdition(Long editionId) throws CustomException;

	BookResponseDto getBookByEditionId(Long editionId);

	DetailDto getDetailByEditionId(Long editionId);

}
