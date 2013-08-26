package ch.bfh.swos.bookapp.service.impl;

import ch.bfh.swos.bookapp.model.Book;
import ch.bfh.swos.bookapp.repository.BookRepository;
import ch.bfh.swos.bookapp.service.BookService;
import ch.bfh.swos.bookapp.service.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Type;
import java.util.Collection;

@Named
public class DefaultBookService implements BookService {

	@Inject
	private BookRepository bookRepository;

	private final ModelMapper mapper = new ModelMapper();

	public BookDTO create(BookDTO bookDto) {
		Book book = mapper.map(bookDto, Book.class);
		Book persistedBook = bookRepository.create(book);
		return mapper.map(persistedBook, BookDTO.class);
	}

	public BookDTO read(Long id) {
		Book book = bookRepository.read(id);
		return mapper.map(book, BookDTO.class);
	}

	public Collection<BookDTO> list() {
		Collection<Book> books = bookRepository.list();
		Type listType = new TypeToken<Collection<BookDTO>>() {
		}.getType();
		return mapper.map(books, listType);
	}

	public BookDTO update(BookDTO bookDto) {
		Book book = mapper.map(bookDto, Book.class);
		Book updatedBook = bookRepository.update(book);
		return mapper.map(updatedBook, BookDTO.class);
	}

	public void delete(BookDTO bookDto) {
		Book book = bookRepository.read(bookDto.getId());
		bookRepository.delete(book);
	}

}
