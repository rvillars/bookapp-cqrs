package ch.bfh.swos.bookapp.vertx.controller;

import ch.bfh.swos.bookapp.query.dto.AuthorDTO;
import ch.bfh.swos.bookapp.query.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Inject
	private AuthorRepository authorRepository;

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<AuthorDTO> list() {
		System.out.println("Collection of Author requested");
		return authorRepository.list();
	}

}
