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

import cl.springmachine.api.spring.boot.jpa.dtos.EditionBookResponseDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionRequestDto;
import cl.springmachine.api.spring.boot.jpa.dtos.EditionResponseDto;
import cl.springmachine.api.spring.boot.jpa.exceptions.CustomException;
import cl.springmachine.api.spring.boot.jpa.services.EditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("editions")
@RequiredArgsConstructor
@Validated
public class EditionController {

	private final EditionService editionService;

	@GetMapping("")
	ResponseEntity<List<EditionResponseDto>> getAllEditions() {
		return new ResponseEntity<>(editionService.getAllEditions(), HttpStatus.OK);
	}

	@GetMapping("{editionId}")
	ResponseEntity<EditionBookResponseDto> getEditionById(@PathVariable Long editionId) {
		return new ResponseEntity<>(editionService.getEditionById(editionId), HttpStatus.OK);
	}

	@PostMapping("")
	ResponseEntity<EditionBookResponseDto> saveEdition(@Valid @RequestBody EditionRequestDto request)
			throws CustomException {
		return new ResponseEntity<>(editionService.saveEdition(request), HttpStatus.CREATED);
	}

	@PatchMapping("{editionId}")
	ResponseEntity<EditionBookResponseDto> updateEdition(@PathVariable Long editionId,
			@RequestBody EditionRequestDto request) throws CustomException {
		return new ResponseEntity<>(editionService.updateEdition(request, editionId), HttpStatus.OK);
	}

	@DeleteMapping("{editionId}")
	ResponseEntity<Void> deleteEdition(@PathVariable Long editionId) throws CustomException {
		editionService.deleteEdition(editionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
