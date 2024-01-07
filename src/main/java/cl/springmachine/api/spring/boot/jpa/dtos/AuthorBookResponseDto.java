package cl.springmachine.api.spring.boot.jpa.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorBookResponseDto {

	private Long id;

	private String name;

	private String nationality;

	private List<BookResponseDto> books;

}
