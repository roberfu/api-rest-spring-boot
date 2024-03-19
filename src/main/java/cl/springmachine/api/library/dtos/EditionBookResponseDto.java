package cl.springmachine.api.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditionBookResponseDto {

	private Long id;

	private String isbn;

	private BookResponseDto book;

	private DetailDto details;

}
