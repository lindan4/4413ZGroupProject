package mappers;

import bean.AuthorBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorRowMapper implements RowMapper<AuthorBean> {
    @Override
    public AuthorBean mapRow(ResultSet rs, int i) throws SQLException {
        AuthorBean bookAuthor = new AuthorBean();

        bookAuthor.setAuthorId(rs.getString("author_id"));
        bookAuthor.setAuthorName(rs.getString("author"));

        return bookAuthor;
    }
}
