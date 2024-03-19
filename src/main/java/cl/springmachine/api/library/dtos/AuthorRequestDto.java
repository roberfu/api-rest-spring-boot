package cl.springmachine.api.library.dtos;

import jakarta.validation.constraints.NotBlank;
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
public class AuthorRequestDto {

	@NotBlank(message = "Name Is Required")
	private String name;

	@NotBlank(message = "Nationality Is Required")
	private String nationality;

}
