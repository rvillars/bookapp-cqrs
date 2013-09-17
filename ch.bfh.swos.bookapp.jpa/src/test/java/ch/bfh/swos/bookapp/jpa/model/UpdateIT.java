/**
 * 
 */
package ch.bfh.swos.bookapp.jpa.model;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * @author rovi
 * 
 */
public class UpdateIT {

	@Test
	public void test() {

		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.swos.bookapp.book.domain").createEntityManager();

		Query q = em.createQuery("select a from Author a");
		@SuppressWarnings("unchecked")
		List<Author> foundAuthors = q.getResultList();
		Author firstAuthor = foundAuthors.get(0);

		// Write access needs a transaction
		em.getTransaction().begin();
		firstAuthor.setLastname("OtherName");
		em.getTransaction().commit();
		// Entity is persisted automatically after commit because it is managed
		// by jpa.

		@SuppressWarnings("unchecked")
		List<Author> updatedAuthors = q.getResultList();
		Author updatedAuthor = updatedAuthors.get(0);
		Assert.assertTrue(updatedAuthor.getLastname().equals("OtherName"));
	}

}
