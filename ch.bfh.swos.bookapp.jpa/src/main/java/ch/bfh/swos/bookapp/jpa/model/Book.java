package ch.bfh.swos.bookapp.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

/**
 * Entity implementation class for Entity: Book
 * 
 */
@Entity
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

    private String bookId;
	private String title;
	@Temporal(DATE)
	private Date releaseDate;
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Author author;

	public Book() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
