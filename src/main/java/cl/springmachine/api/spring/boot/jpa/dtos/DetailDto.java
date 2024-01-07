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
public class DetailDto {

	@NotBlank(message = "Language Is Required")
	private String language;

	@NotBlank(message = "Pages Is Required")
	private String pages;

}
