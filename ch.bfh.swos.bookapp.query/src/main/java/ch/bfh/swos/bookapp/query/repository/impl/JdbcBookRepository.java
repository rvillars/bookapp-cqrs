package ch.bfh.swos.bookapp.query.repository.impl;

import ch.bfh.swos.bookapp.query.dto.AuthorDTO;
import ch.bfh.swos.bookapp.query.dto.BookDTO;
import ch.bfh.swos.bookapp.query.repository.AuthorRepository;
import ch.bfh.swos.bookapp.query.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: rovi
 * Date: 12.10.13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
@Named
public class JdbcBookRepository extends JdbcDaoSupport implements BookRepository {

    @Inject
    public JdbcBookRepository(DataSource dataSource){
        super();
        setDataSource(dataSource);
    }

    @Override
    public Collection<BookDTO> list() {
        return getJdbcTemplate().query("SELECT * FROM BOOK book, AUTHOR author WHERE book.AUTHOR_ID = author.ID",
                new BookRowMapper());
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update("DELETE * FROM BOOK");
    }

    private class BookRowMapper implements RowMapper<BookDTO> {

        @Override
        public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getString("BOOKID"));
            book.setTitle(rs.getString("TITLE"));
            book.setReleaseDate(rs.getDate("RELEASEDATE"));

            AuthorDTO author = new AuthorDTO();
            author.setAuthorId(rs.getString("AUTHORID"));
            author.setFirstname(rs.getString("FIRSTNAME"));
            author.setLastname(rs.getString("LASTNAME"));

            book.setAuthor(author);
            return book;
        }
    }
}
