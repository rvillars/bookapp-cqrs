package ch.bfh.swos.bookapp.service;

import ch.bfh.swos.bookapp.service.dto.AuthorDTO;

import java.util.Collection;

public interface AuthorService {

	public AuthorDTO create(AuthorDTO authorDto);

	public AuthorDTO read(long id);

	public Collection<AuthorDTO> list();

	public AuthorDTO update(AuthorDTO book);

	public void delete(AuthorDTO book);

    public void deleteAll();
}
