package ch.bfh.swos.bookapp.rest.controller;

import ch.bfh.swos.bookapp.service.BookService;
import ch.bfh.swos.bookapp.service.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/books")
public class BookController {

	@Inject
	private BookService bookService;

	/**
	 * Create
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public BookDTO create(@RequestBody BookDTO book) {
		BookDTO createdBook = bookService.create(book);
		System.out.println("Book created with id = " + createdBook.getId());
		return createdBook;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<BookDTO> list() {
		System.out.println("Collection of Book requested");
		return bookService.list();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public BookDTO read(@PathVariable Long id) {
		System.out.println("Book requested with id = " + id);
		return bookService.read(id);
	}

	/**
	 * Update
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BookDTO update(@RequestBody BookDTO book, @PathVariable long id) {
		BookDTO updatedBook = bookService.update(book);
		System.out.println("Book updated with id = " + updatedBook.getId());
		return updatedBook;
	}

	/**
	 * Delete
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		BookDTO book = bookService.read(id);
		bookService.delete(book);
		System.out.println("Delete Book with id = " + id);
	}
}
