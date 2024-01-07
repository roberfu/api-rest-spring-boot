package cl.springmachine.api.spring.boot.jpa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {

	private Long id;

	private String title;

	private String genre;

}