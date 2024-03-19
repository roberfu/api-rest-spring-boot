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
public class DetailDto {

	@NotBlank(message = "Language Is Required")
	private String language;

	@NotBlank(message = "Pages Is Required")
	private String pages;

}
