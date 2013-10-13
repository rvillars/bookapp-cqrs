package ch.bfh.swos.bookapp.query.repository;

import ch.bfh.swos.bookapp.query.dto.BookDTO;

import java.util.Collection;

public interface BookRepository {

	public Collection<BookDTO> list();

    public void deleteAll();
}
