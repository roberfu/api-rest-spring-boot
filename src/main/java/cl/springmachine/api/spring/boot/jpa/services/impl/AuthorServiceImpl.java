package cl.springmachine.api.spring.boot.jpa.services.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.springmachine.api.spring.boot.jpa.dtos.AuthorBookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.AuthorRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.AuthorResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.entities.Author;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;
import cl.springmachine.api.spring.boot.jpa.exceptions.ResourceNotFoundException;
import cl.springmachine.api.spring.boot.jpa.repositories.AuthorRepository;
import cl.springmachine.api.spring.boot.jpa.repositories.BookRepository;
import cl.springmachine.api.spring.boot.jpa.services.AuthorService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	private final BookRepository bookRepository;

	private static final String AUTHOR_NOT_FOUND = "Author Not Found";

	@Override
	public List<AuthorResponseDto> getAllAuthors() {
		return authorRepository.findAll().stream().map(t -> AuthorResponseDto.builder().id(t.getId()).name(t.getName())
				.nationality(t.getNationality()).build()).toList();
	}

	@Override
	public AuthorBookResponseDto getAuthorById(Long authorId) {
		return authorRepository.findById(authorId)
				.map(t -> AuthorBookResponseDto.builder().id(t.getId()).name(t.getName())
						.nationality(t.getNationality()).books(getAllBooksByAuthorId(t.getId())).build())
				.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
	}

	@Override
	@Transactional
	public AuthorResponseDto saveAuthor(AuthorRequestDto request) throws CustomException {
		Author author = null;
		try {
			author = authorRepository
					.save(new Author(null, request.getName(), request.getNationality(), new HashSet<>()));
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return AuthorResponseDto.builder().id(author.getId()).name(author.getName())
				.nationality(author.getNationality()).build();
	}

	@Override
	@Transactional
	public AuthorResponseDto updateAuthor(AuthorRequestDto request, Long authorId) throws CustomException {
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
		if (request.getName() != null)
			author.setName(request.getName());
		if (request.getNationality() != null)
			author.setNationality(request.getNationality());
		try {
			authorRepository.save(author);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

		return AuthorResponseDto.builder().id(author.getId()).name(author.getName())
				.nationality(author.getNationality()).build();
	}

	@Override
	@Transactional
	public void deleteAuthor(Long authorId) throws CustomException {
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
		try {
			authorRepository.deleteById(author.getId());
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	@Override
	public List<BookResponseDto> getAllBooksByAuthorId(Long authorId) {
		return bookRepository.findAllByAuthorsId(authorId).stream()
				.map(t -> BookResponseDto.builder().id(t.getId()).title(t.getTitle()).genre(t.getGenre()).build())
				.toList();
	}

}