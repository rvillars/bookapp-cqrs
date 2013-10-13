package ch.bfh.swos.bookapp.query.repository.impl;

import ch.bfh.swos.bookapp.query.dto.AuthorDTO;
import ch.bfh.swos.bookapp.query.repository.AuthorRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 12.10.13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
@Named
public class JdbcAuthorRepository extends JdbcDaoSupport implements AuthorRepository {

    @Inject
    public JdbcAuthorRepository(DataSource dataSource){
        super();
        setDataSource(dataSource);
    }

    @Override
    public Collection<AuthorDTO> list() {
        return getJdbcTemplate().query("SELECT * FROM AUTHOR",
                new BeanPropertyRowMapper(AuthorDTO.class));
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update("DELETE FROM AUTHOR");
        System.out.println("Deleted AUTHOR table");
    }
}
