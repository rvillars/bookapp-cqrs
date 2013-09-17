package ch.bfh.swos.bookapp.jpa.model;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CreateIT {

	@Test
	public void test() {
		Author author = new Author();
		author.setFirstname("J.R.R");
		author.setLastname("Tolkien");

		Book book = new Book();
		book.setTitle("Der Herr der Ringe - Die Gefaehrten");
		book.setReleaseDate(new Date());

		Book book2 = new Book();
		book2.setTitle("Der Herr der Ringe - Die zwei Tuerme");
		book2.setReleaseDate(new Date());

		Set<Book> books = new HashSet<Book>();
		books.add(book);
		books.add(book2);
		author.setBooks(books);
		book.setAuthor(author);
		book2.setAuthor(author);

		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.swos.bookapp.book.domain").createEntityManager();

		em.getTransaction().begin();
		em.persist(author);
		em.getTransaction().commit();
	}

}
