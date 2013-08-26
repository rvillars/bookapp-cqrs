package ch.bfh.swos.bookapp.repository.impl;

import ch.bfh.swos.bookapp.model.Book;
import ch.bfh.swos.bookapp.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Named
public class JPABookRepository implements BookRepository {

	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional
	public Book create(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Book read(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Book> list() {
		return em.createQuery("select b from Book b").getResultList();
	}

	@Override
	@Transactional
	public Book update(Book book) {
		return em.merge(book);
	}

	@Override
	@Transactional
	public void delete(Book book) {
        em.remove(em.merge(book));
	}

    @Override
    public Book readByBookId(String bookId) {
        Query query = em.createQuery("select book from Book book where book.bookId = :bookId");
        query.setParameter("bookId", bookId);
        return (Book)query.getSingleResult();
    }

}
