package ch.bfh.swos.bookapp.repository;

import ch.bfh.swos.bookapp.model.Author;

import java.util.Collection;

public interface AuthorRepository {

	public Author create(Author author);

	public Author read(long id);

	public Collection<Author> list();

	public Author update(Author author);

	public void delete(Author author);

    public Author readByAuthorId(String authorId);

}
