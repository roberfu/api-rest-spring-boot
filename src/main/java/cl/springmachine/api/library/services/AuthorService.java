package cl.springmachine.api.library.services;

import java.util.List;

import cl.springmachine.api.library.dtos.AuthorBookResponseDto;
import cl.springmachine.api.library.dtos.AuthorRequestDto;
import cl.springmachine.api.library.dtos.AuthorResponseDto;
import cl.springmachine.api.library.dtos.BookResponseDto;
import cl.springmachine.api.library.exceptions.CustomException;

public interface AuthorService {

	List<AuthorResponseDto> getAllAuthors();

	AuthorBookResponseDto getAuthorById(Long authorId);

	AuthorResponseDto saveAuthor(AuthorRequestDto request) throws CustomException;

	AuthorResponseDto updateAuthor(AuthorRequestDto request, Long authorId) throws CustomException;

	void deleteAuthor(Long authorId) throws CustomException;

	List<BookResponseDto> getAllBooksByAuthorId(Long authorId);

}
