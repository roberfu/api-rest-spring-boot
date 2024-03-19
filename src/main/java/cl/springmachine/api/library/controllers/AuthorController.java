package cl.springmachine.api.library.controllers;

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

import cl.springmachine.api.library.dtos.AuthorBookResponseDto;
import cl.springmachine.api.library.dtos.AuthorRequestDto;
import cl.springmachine.api.library.dtos.AuthorResponseDto;
import cl.springmachine.api.library.dtos.BookResponseDto;
import cl.springmachine.api.library.exceptions.CustomException;
import cl.springmachine.api.library.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {

	private final AuthorService authorService;

	@GetMapping("")
	ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
		return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
	}

	@GetMapping("/{authorId}")
	ResponseEntity<AuthorBookResponseDto> getAuthorById(@PathVariable(value = "authorId") Long authorId) {
		return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
	}

	@PostMapping("")
	ResponseEntity<AuthorResponseDto> saveAuthor(@Valid @RequestBody AuthorRequestDto request) throws CustomException {
		return new ResponseEntity<>(authorService.saveAuthor(request), HttpStatus.CREATED);
	}

	@PatchMapping("/{authorId}")
	ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable(value = "authorId") Long authorId,
			@RequestBody AuthorRequestDto request) throws CustomException {
		return new ResponseEntity<>(authorService.updateAuthor(request, authorId), HttpStatus.OK);
	}

	@DeleteMapping("/{authorId}")
	ResponseEntity<Void> deleteAuthor(@PathVariable(value = "authorId") Long authorId) throws CustomException {
		authorService.deleteAuthor(authorId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{authorId}/books")
	ResponseEntity<List<BookResponseDto>> getAllBooksByAuthorId(@PathVariable(value = "authorId") Long authorId) {
		return new ResponseEntity<>(authorService.getAllBooksByAuthorId(authorId), HttpStatus.OK);
	}
}