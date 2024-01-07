package cl.springmachine.api.spring.boot.jpa.services;

import java.util.List;

import cl.springmachine.api.spring.boot.jpa.dtos.AuthorBookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.AuthorRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.AuthorResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;

public interface AuthorService {

	List<AuthorResponseDto> getAllAuthors();

	AuthorBookResponseDto getAuthorById(Long authorId);

	AuthorResponseDto saveAuthor(AuthorRequestDto request) throws CustomException;

	AuthorResponseDto updateAuthor(AuthorRequestDto request, Long authorId) throws CustomException;

	void deleteAuthor(Long authorId) throws CustomException;

	List<BookResponseDto> getAllBooksByAuthorId(Long authorId);

}
