package ch.bfh.swos.bookapp.service;

import ch.bfh.swos.bookapp.service.dto.BookDTO;

import java.util.Collection;

public interface BookService {

	public BookDTO create(BookDTO book);

	public BookDTO read(Long id);

	public Collection<BookDTO> list();

	public BookDTO update(BookDTO book);

	public void delete(BookDTO book);
}
