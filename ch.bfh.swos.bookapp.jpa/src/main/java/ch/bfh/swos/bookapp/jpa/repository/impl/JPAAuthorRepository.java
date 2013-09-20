package ch.bfh.swos.bookapp.jpa.repository.impl;

import ch.bfh.swos.bookapp.jpa.model.Author;
import ch.bfh.swos.bookapp.jpa.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Named
public class JPAAuthorRepository implements AuthorRepository {

	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional
	public Author create(Author author) {
		em.persist(author);
		return author;
	}

	@Override
	public Author read(long id) {
		return em.find(Author.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Author> list() {
		return em.createQuery("select a from Author a").getResultList();
	}

	@Override
	@Transactional
	public Author update(Author author) {
		return em.merge(author);
	}

	@Override
	@Transactional
	public void delete(Author author) {
        em.remove(em.merge(author));
	}

    @Override
    @Transactional
    public void deleteAll() {
        em.createQuery("delete from Author").executeUpdate();
    }

    @Override
    @Transactional
    public Author readByAuthorId(String authorId) {
        Query query = em.createQuery("select a from Author a where a.authorId = :authorId");
        query.setParameter("authorId", authorId);
        return (Author)query.getSingleResult();
    }

}
