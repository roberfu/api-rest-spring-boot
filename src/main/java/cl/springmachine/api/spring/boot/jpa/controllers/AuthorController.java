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

import cl.springmachine.api.spring.boot.jpa.dtos.AuthorBookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.AuthorRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.AuthorResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;
import cl.springmachine.api.spring.boot.jpa.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {

	private final AuthorService authorService;

	@GetMapping("")
	ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
		return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
	}

	@GetMapping("/{authorId}")
	ResponseEntity<AuthorBookResponseDto> getAuthorById(@PathVariable Long authorId) {
		return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
	}

	@PostMapping("")
	ResponseEntity<AuthorResponseDto> saveAuthor(@Valid @RequestBody AuthorRequestDto request) throws CustomException {
		return new ResponseEntity<>(authorService.saveAuthor(request), HttpStatus.CREATED);
	}

	@PatchMapping("/{authorId}")
	ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorRequestDto request)
			throws CustomException {
		return new ResponseEntity<>(authorService.updateAuthor(request, authorId), HttpStatus.OK);
	}

	@DeleteMapping("/{authorId}")
	ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId) throws CustomException {
		authorService.deleteAuthor(authorId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{authorId}/books")
	ResponseEntity<List<BookResponseDto>> getAllBooksByAuthorId(@PathVariable Long authorId) {
		return new ResponseEntity<>(authorService.getAllBooksByAuthorId(authorId), HttpStatus.OK);
	}
}