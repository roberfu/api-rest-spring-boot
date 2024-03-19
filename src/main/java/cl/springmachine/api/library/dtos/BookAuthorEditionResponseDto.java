package cl.springmachine.api.library.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAuthorEditionResponseDto {

	private Long id;

	private String title;

	private String genre;

	private List<AuthorResponseDto> authors;

	private List<EditionResponseDto> editions;

}
