package cl.springmachine.api.spring.boot.jpa.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.springmachine.api.spring.boot.jpa.dtos.AuthorResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookAuthorEditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.BookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.entities.Author;
import cl.springmachine.api.spring.boot.jpa.entities.Book;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;
import cl.springmachine.api.spring.boot.jpa.exceptions.ResourceNotFoundException;
import cl.springmachine.api.spring.boot.jpa.repositories.AuthorRepository;
import cl.springmachine.api.spring.boot.jpa.repositories.BookRepository;
import cl.springmachine.api.spring.boot.jpa.repositories.EditionRepository;
import cl.springmachine.api.spring.boot.jpa.services.BookService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	private final AuthorRepository authorRepository;

	private final EditionRepository editionRepository;

	private static final String BOOK_NOT_FOUND = "Book Not Found";

	private static final String AUTHOR_NOT_FOUND = "Author Not Found";

	@Override
	public List<BookResponseDto> getAllBooks() {
		return bookRepository.findAll().stream()
				.map(t -> BookResponseDto.builder().id(t.getId()).title(t.getTitle()).genre(t.getGenre()).build())
				.toList();
	}

	@Override
	public BookAuthorEditionResponseDto getBookById(Long bookId) {
		return bookRepository.findById(bookId)
				.map(t -> BookAuthorEditionResponseDto.builder().id(t.getId()).title(t.getTitle()).genre(t.getGenre())
						.authors(getAllAuthorsByBookId(t.getId())).editions(getAllEditionsByBookId(bookId)).build())
				.orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
	}

	@Override
	@Transactional
	public BookResponseDto saveBook(BookRequestDto request) throws CustomException {
		Book book;
		try {
			book = bookRepository
					.save(new Book(null, request.getTitle(), request.getGenre(), new HashSet<>(), new ArrayList<>()));
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return BookResponseDto.builder().id(book.getId()).title(book.getTitle()).genre(book.getGenre()).build();
	}

	@Override
	@Transactional
	public BookResponseDto updateBook(BookRequestDto request, Long bookId) throws CustomException {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book"));
		if (request.getTitle() != null)
			book.setTitle(request.getTitle());
		if (request.getGenre() != null)
			book.setGenre(request.getGenre());
		try {
			bookRepository.save(book);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return BookResponseDto.builder().id(book.getId()).title(book.getTitle()).genre(book.getGenre()).build();
	}

	@Override
	@Transactional
	public void deleteBook(Long bookId) throws CustomException {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		try {
			bookRepository.deleteById(book.getId());
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public List<AuthorResponseDto> getAllAuthorsByBookId(Long bookId) {
		return authorRepository.findAllByBooksId(bookId).stream().map(t -> AuthorResponseDto.builder().id(t.getId())
				.name(t.getName()).nationality(t.getNationality()).build()).toList();
	}

	@Override
	@Transactional
	public void addAuthorToBook(Long bookId, Long authorId) throws CustomException {
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		try {
			book.addAuthor(author);
			bookRepository.save(book);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void removeAuthorFromBook(Long bookId, Long authorId) throws CustomException {
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		try {
			book.removeAuthor(author);
			bookRepository.save(book);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public void addAuthorsToBook(List<String> request, Long bookId) throws CustomException {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		for (String authorId : request) {
			Author author = authorRepository.findById(Long.parseLong(authorId))
					.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
			book.addAuthor(author);
		}
		try {
			bookRepository.save(book);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		bookRepository.save(book);
	}

	@Override
	public List<EditionResponseDto> getAllEditionsByBookId(Long bookId) {
		return editionRepository.findAllByBookId(bookId).stream()
				.map(t -> EditionResponseDto.builder().id(t.getId()).isbn(t.getIsbn()).build()).toList();
	}

}