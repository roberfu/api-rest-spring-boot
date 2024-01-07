package cl.springmachine.api.spring.boot.jpa.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String genre;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();

	public void addAuthor(Author author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}

	public void removeAuthor(Author author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Edition> editions = new ArrayList<>();

	public void addEdition(Edition edition) {
		this.editions.add(edition);
		edition.setBook(this);
	}

	public void removeEdition(Edition edition) {
		edition.setBook(null);
		this.editions.remove(edition);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		}
		return id != null && id.equals(((Book) obj).id);
	}

	@Override
	public int hashCode() {
		return 2023;
	}

}
