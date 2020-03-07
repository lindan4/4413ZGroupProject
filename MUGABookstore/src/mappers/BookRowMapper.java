package mappers;

import bean.BookBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<BookBean> {
    @Override
    public BookBean mapRow(ResultSet rs, int i) throws SQLException {
        BookBean book = new BookBean();

        book.setBid(rs.getString("bid"));
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getInt("price"));
        book.setCategory(rs.getString("category"));

        return book;
    }
}
