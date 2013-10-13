package ch.bfh.swos.bookapp.query.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity implementation class for Entity: Book
 * 
 */
public class BookDTO implements Serializable {

    private String bookId;
	private String title;
	private Date releaseDate;

	private AuthorDTO author;

	public BookDTO() {
		super();
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

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
}
