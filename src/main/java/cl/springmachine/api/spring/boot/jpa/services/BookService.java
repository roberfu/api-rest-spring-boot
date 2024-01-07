package cl.springmachine.api.spring.boot.jpa.services;

import java.util.List;

import cl.springmachine.api.spring.boot.jpa.dtos.AuthorResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookAuthorEditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;

public interface BookService {

	List<BookResponseDto> getAllBooks();

	BookAuthorEditionResponseDto getBookById(Long bookId);

	BookResponseDto saveBook(BookRequestDto request) throws CustomException;

	BookResponseDto updateBook(BookRequestDto request, Long bookId) throws CustomException;

	void deleteBook(Long bookId) throws CustomException;

	List<AuthorResponseDto> getAllAuthorsByBookId(Long bookId);

	void addAuthorToBook(Long bookId, Long authorId) throws CustomException;

	void removeAuthorFromBook(Long bookId, Long authorId) throws CustomException;

	void addAuthorsToBook(List<String> request, Long bookId) throws CustomException;

	List<EditionResponseDto> getAllEditionsByBookId(Long bookId);

}
