package mappers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bean.BookBean;

@Component
public class BookRowMapper implements RowMapper<BookBean> {
    @Override
    public BookBean mapRow(ResultSet rs, int i) throws SQLException {
        BookBean book = new BookBean();

        book.setBid(rs.getString("bid"));
        book.setTitle(rs.getString("title"));
        book.setDescription(rs.getString("description"));
        book.setEdition(rs.getString("edition"));
        book.setFormat(rs.getString("format"));
        book.setIsbn(new BigDecimal(rs.getString("isbn").replaceAll("10:", "").replaceAll("13:", "")));
        book.setPages(rs.getInt("pages"));
        book.setRating(rs.getDouble("rating"));
        book.setRating_count(rs.getInt("rating_count"));
        book.setImageUrl(rs.getString("image_url"));
        book.setPrice(rs.getDouble("price"));

        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if ("count".equals(rsmd.getColumnName(x))) {
                book.setCount(rs.getInt("count"));
            }
        }

        book.setAuthors(new ArrayList<>());
        book.setCategories(new ArrayList<>());

        return book;
    }
}
