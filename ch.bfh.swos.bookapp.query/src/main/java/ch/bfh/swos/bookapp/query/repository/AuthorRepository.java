package ch.bfh.swos.bookapp.query.repository;

import ch.bfh.swos.bookapp.query.dto.AuthorDTO;

import java.util.Collection;

public interface AuthorRepository {

	public Collection<AuthorDTO> list();

    public void deleteAll();

}
