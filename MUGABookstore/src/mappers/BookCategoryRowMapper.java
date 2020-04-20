package mappers;

import bean.BookCategoryBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookCategoryRowMapper implements RowMapper<BookCategoryBean> {
    @Override
    public BookCategoryBean mapRow(ResultSet rs, int i) throws SQLException {
        BookCategoryBean category = new BookCategoryBean();

        category.setCategoryId(rs.getString("category_id"));
        category.setBid(rs.getString("bid"));

        return category;
    }
}
