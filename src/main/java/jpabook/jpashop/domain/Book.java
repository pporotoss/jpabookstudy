package jpabook.jpashop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")	// 구분컬럼에 입력할 값을 설정. 기본값은 클래스명 사용.
public class Book extends Item {
	
	private String author;
	private String isbn;

	// Getter, Setter
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
