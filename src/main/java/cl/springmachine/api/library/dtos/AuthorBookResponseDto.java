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
public class AuthorBookResponseDto {

	private Long id;

	private String name;

	private String nationality;

	private List<BookResponseDto> books;

}
