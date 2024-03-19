package cl.springmachine.api.library.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditionRequestDto {

	@NotBlank(message = "ISBN Is Required")
	private String isbn;

	@JsonProperty("book_id")
	@NotBlank(message = "Book Id Is Required")
	private String bookId;

	@NotNull(message = "Details Is Required")
	@Valid
	private DetailDto details;
}
