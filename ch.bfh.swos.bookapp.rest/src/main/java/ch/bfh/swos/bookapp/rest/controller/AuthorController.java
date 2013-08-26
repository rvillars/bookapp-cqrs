package ch.bfh.swos.bookapp.rest.controller;

import ch.bfh.swos.bookapp.service.AuthorService;
import ch.bfh.swos.bookapp.service.dto.AuthorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Inject
	private AuthorService authorService;

	/**
	 * Create
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public AuthorDTO create(@RequestBody AuthorDTO author) {
		AuthorDTO createdAuthor = authorService.create(author);
		System.out.println("Author created with id = " + createdAuthor.getId());
		return createdAuthor;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<AuthorDTO> list() {
		System.out.println("Collection of Author requested");
		return authorService.list();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public AuthorDTO read(@PathVariable long id) {
		System.out.println("Book requested with id = " + id);
		return authorService.read(id);
	}

	/**
	 * Update
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public AuthorDTO update(@RequestBody AuthorDTO author, @PathVariable long id) {
		AuthorDTO updatedAuthor = authorService.update(author);
		System.out.println("Author updated with id = " + updatedAuthor.getId());
		return updatedAuthor;
	}

	/**
	 * Delete
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable long id) {
		AuthorDTO author = authorService.read(id);
		authorService.delete(author);
		System.out.println("Delete Author with id = " + id);
	}
}
