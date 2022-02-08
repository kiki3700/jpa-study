package jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item{
	private String autor;
	private String isbn;
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
