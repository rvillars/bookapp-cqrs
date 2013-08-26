package ch.bfh.swos.bookapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: Author
 * 
 */
@Entity
public class Author implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

    private String authorId;
    private String firstname;
	private String lastname;
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "author", cascade = { PERSIST, REMOVE })
	private Set<Book> books;

	public Author() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
