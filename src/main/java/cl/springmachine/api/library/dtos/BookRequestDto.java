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
public class BookRequestDto {

	@NotBlank(message = "Title Is Required")
	private String title;

	@NotBlank(message = "Genre Is Required")
	private String genre;

}
