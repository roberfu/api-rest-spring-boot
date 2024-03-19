package cl.springmachine.api.library.services;

import java.util.List;

import cl.springmachine.api.library.dtos.BookResponseDto;
import cl.springmachine.api.library.dtos.DetailDto;
import cl.springmachine.api.library.dtos.EditionBookResponseDto;
import cl.springmachine.api.library.dtos.EditionRequestDto;
import cl.springmachine.api.library.dtos.EditionResponseDto;
import cl.springmachine.api.library.exceptions.CustomException;

public interface EditionService {

	List<EditionResponseDto> getAllEditions();

	EditionBookResponseDto getEditionById(Long editionId);

	EditionBookResponseDto saveEdition(EditionRequestDto request) throws CustomException;

	EditionBookResponseDto updateEdition(EditionRequestDto request, Long editionId) throws CustomException;

	void deleteEdition(Long editionId) throws CustomException;

	BookResponseDto getBookByEditionId(Long editionId);

	DetailDto getDetailByEditionId(Long editionId);

}
