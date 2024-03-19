package cl.springmachine.api.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponseDto {

	private Long id;

	private String name;

	private String nationality;

}
