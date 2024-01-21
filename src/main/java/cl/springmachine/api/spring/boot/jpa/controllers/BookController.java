package cl.springmachine.api.spring.boot.jpa.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.springmachine.api.spring.boot.jpa.dtos.AuthorResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookAuthorEditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;
import cl.springmachine.api.spring.boot.jpa.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/books")
@RequiredArgsConstructor
@Validated
public class BookController {

	private final BookService bookService;

	@GetMapping("")
	ResponseEntity<List<BookResponseDto>> getAllBooks() {
		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	ResponseEntity<BookAuthorEditionResponseDto> getBookById(@PathVariable Long bookId) {
		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
	}

	@PostMapping("")
	ResponseEntity<BookResponseDto> saveBook(@Valid @RequestBody BookRequestDto request) throws CustomException {
		return new ResponseEntity<>(bookService.saveBook(request), HttpStatus.CREATED);
	}

	@PatchMapping("/{bookId}")
	ResponseEntity<BookResponseDto> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto request)
			throws CustomException {
		return new ResponseEntity<>(bookService.updateBook(request, bookId), HttpStatus.OK);
	}

	@DeleteMapping("/{bookId}")
	ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws CustomException {
		bookService.deleteBook(bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{bookId}/authors")
	ResponseEntity<List<AuthorResponseDto>> getAllAuthorsByBookId(@PathVariable Long bookId) {
		return new ResponseEntity<>(bookService.getAllAuthorsByBookId(bookId), HttpStatus.OK);
	}

	@PostMapping("/{bookId}/authors/{authorId}")
	ResponseEntity<Void> addAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId)
			throws CustomException {
		bookService.addAuthorToBook(bookId, authorId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{bookId}/authors/{authorId}")
	ResponseEntity<Void> removeBookFromAuthor(@PathVariable Long bookId, @PathVariable Long authorId)
			throws CustomException {
		bookService.removeAuthorFromBook(bookId, authorId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/{bookId}/authors")
	ResponseEntity<Void> addAuthorsToBook(@PathVariable Long bookId, @RequestBody List<String> request)
			throws CustomException {
		bookService.addAuthorsToBook(request, bookId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
