package cl.springmachine.api.library.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
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
