package ch.bfh.swos.bookapp.service;

import ch.bfh.swos.bookapp.jpa.model.Book;
import ch.bfh.swos.bookapp.jpa.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class PersistenceServiceIT {

	@Inject
	private BookRepository bookRepository;

	@Test
	public void test() {

		// Create
		Book newBook = new Book();
		newBook.setTitle("Test");
		bookRepository.create(newBook);

		// Read
		Book readBook = bookRepository.read(newBook.getId());
		Assert.assertTrue(newBook.getTitle().equals(readBook.getTitle()));

		// Update
		readBook.setTitle("Test2");
		readBook = bookRepository.update(readBook);
		Book updatedBook = bookRepository.read(readBook.getId());
		Assert.assertTrue(readBook.getTitle().equals(updatedBook.getTitle()));

		// Delete
		bookRepository.delete(updatedBook);
		Book deletedBook = bookRepository.read(readBook.getId());
		Assert.assertNull(deletedBook);
	}

}
