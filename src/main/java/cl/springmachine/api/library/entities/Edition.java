package cl.springmachine.api.library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "edition")
public class Edition {

	@Id
	private Long id;

	private String isbn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detail_id")
	private Detail detail;

}
