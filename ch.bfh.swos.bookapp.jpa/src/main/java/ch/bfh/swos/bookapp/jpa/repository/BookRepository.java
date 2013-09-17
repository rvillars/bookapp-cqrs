package ch.bfh.swos.bookapp.jpa.repository;

import ch.bfh.swos.bookapp.jpa.model.Book;

import java.util.Collection;

public interface BookRepository {

	public Book create(Book book);

	public Book read(Long id);

	public Collection<Book> list();

	public Book update(Book book);

	public void delete(Book book);

    public Book readByBookId(String bookId);

}
