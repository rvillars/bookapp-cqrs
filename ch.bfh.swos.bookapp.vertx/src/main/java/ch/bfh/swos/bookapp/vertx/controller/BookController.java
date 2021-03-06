package ch.bfh.swos.bookapp.vertx.controller;

import ch.bfh.swos.bookapp.query.dto.BookDTO;
import ch.bfh.swos.bookapp.query.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/books")
public class BookController {

	@Inject
	private BookRepository bookRepository;

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<BookDTO> list() {
		System.out.println("Collection of Book requested");
		return bookRepository.list();
	}

}
