package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bean.CategoryBean;

@Component
public class CategoryRowMapper implements RowMapper<CategoryBean> {
    @Override
    public CategoryBean mapRow(ResultSet rs, int i) throws SQLException {
        CategoryBean category = new CategoryBean();

        category.setCategory_id(rs.getString("category_id"));
        category.setCategory(rs.getString("category"));

        return category;
    }
}
