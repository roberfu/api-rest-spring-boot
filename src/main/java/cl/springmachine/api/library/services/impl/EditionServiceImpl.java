package cl.springmachine.api.library.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.springmachine.api.library.dtos.BookResponseDto;
import cl.springmachine.api.library.dtos.DetailDto;
import cl.springmachine.api.library.dtos.EditionBookResponseDto;
import cl.springmachine.api.library.dtos.EditionRequestDto;
import cl.springmachine.api.library.dtos.EditionResponseDto;
import cl.springmachine.api.library.entities.Book;
import cl.springmachine.api.library.entities.Detail;
import cl.springmachine.api.library.entities.Edition;
import cl.springmachine.api.library.exceptions.CustomException;
import cl.springmachine.api.library.exceptions.ResourceNotFoundException;
import cl.springmachine.api.library.repositories.BookRepository;
import cl.springmachine.api.library.repositories.DetailRepository;
import cl.springmachine.api.library.repositories.EditionRepository;
import cl.springmachine.api.library.services.EditionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditionServiceImpl implements EditionService {

	private final EditionRepository editionRepository;

	private final BookRepository bookRepository;

	private final DetailRepository detailRepository;

	private static final String EDITION_NOT_FOUND = "Edition Not Found";

	private static final String BOOK_NOT_FOUND = "Book Not Found";

	private static final String DETAIL_NOT_FOUND = "Detail Not Found";

	@Override
	public List<EditionResponseDto> getAllEditions() {
		return editionRepository.findAll().stream().map(t -> EditionResponseDto.builder().id(t.getId())
				.details(getDetailByEditionId(t.getId())).isbn(t.getIsbn()).build()).toList();
	}

	@Override
	public EditionBookResponseDto getEditionById(Long editionId) {
		return editionRepository.findById(editionId)
				.map(t -> EditionBookResponseDto.builder().id(t.getId()).book(getBookByEditionId(t.getId()))
						.details(getDetailByEditionId(editionId)).isbn(t.getIsbn()).build())
				.orElseThrow(() -> new ResourceNotFoundException(EDITION_NOT_FOUND));
	}

	@Override
	@Transactional
	public EditionBookResponseDto saveEdition(EditionRequestDto request) throws CustomException {
		Book book = bookRepository.findById(Long.parseLong(request.getBookId()))
				.orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		Edition edition;
		try {
			Detail detail = detailRepository
					.save(new Detail(null, request.getDetails().getLanguage(), request.getDetails().getPages()));
			edition = editionRepository.save(new Edition(null, request.getIsbn(), book, detail));
			book.getEditions().add(edition);
			bookRepository.save(book);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return EditionBookResponseDto.builder().id(edition.getId()).book(getBookByEditionId(edition.getId()))
				.details(getDetailByEditionId(edition.getId())).isbn(edition.getIsbn()).build();
	}

	@Override
	@Transactional
	public EditionBookResponseDto updateEdition(EditionRequestDto request, Long editionId) throws CustomException {
		Edition edition = editionRepository.findById(editionId)
				.orElseThrow(() -> new ResourceNotFoundException(EDITION_NOT_FOUND));
		Detail detail = detailRepository.findById(editionId)
				.orElseThrow(() -> new ResourceNotFoundException(DETAIL_NOT_FOUND));
		if (request.getIsbn() != null)
			edition.setIsbn(request.getIsbn());
		if (request.getDetails().getLanguage() != null)
			detail.setLanguage(request.getDetails().getLanguage());
		if (request.getDetails().getPages() != null)
			detail.setPages(request.getDetails().getPages());
		try {
			editionRepository.save(edition);
			detailRepository.save(detail);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return EditionBookResponseDto.builder().id(edition.getId()).book(getBookByEditionId(edition.getId()))
				.details(getDetailByEditionId(edition.getId())).isbn(edition.getIsbn()).build();
	}

	@Override
	@Transactional
	public void deleteEdition(Long editionId) throws CustomException {
		Edition edition = editionRepository.findById(editionId)
				.orElseThrow(() -> new ResourceNotFoundException(EDITION_NOT_FOUND));
		try {
			editionRepository.deleteById(edition.getId());
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	@Override
	public BookResponseDto getBookByEditionId(Long editionId) {
		return bookRepository.findOneByEditionsId(editionId)
				.map(t -> BookResponseDto.builder().id(t.getId()).title(t.getTitle()).genre(t.getGenre()).build())
				.orElse(null);
	}

	@Override
	public DetailDto getDetailByEditionId(Long editionId) {
		return detailRepository.findById(editionId)
				.map(t -> DetailDto.builder().language(t.getLanguage()).pages(t.getPages()).build())
				.orElseThrow(() -> new ResourceNotFoundException(DETAIL_NOT_FOUND));
	}

}
