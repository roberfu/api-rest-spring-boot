package cl.springmachine.api.spring.boot.jpa.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDto {

	@NotBlank(message = "Title Is Required")
	private String title;

	@NotBlank(message = "Genre Is Required")
	private String genre;

}
