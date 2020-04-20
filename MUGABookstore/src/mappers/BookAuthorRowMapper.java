package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bean.BookAuthorBean;

@Component
public class BookAuthorRowMapper implements RowMapper<BookAuthorBean> {
    @Override
    public BookAuthorBean mapRow(ResultSet rs, int i) throws SQLException {
        BookAuthorBean bookAuthor = new BookAuthorBean();

        bookAuthor.setAuthorId(rs.getString("author_id"));
        bookAuthor.setBid(rs.getString("bid"));
        bookAuthor.setId(rs.getString("id"));

        return bookAuthor;
    }
}
