package dao;

import bean.CategoryBean;
import mappers.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO {
    private final JdbcTemplate jdbcTemplate;

    private final CategoryRowMapper categoryRowMapper;

    public CategoryDAO(
            final JdbcTemplate jdbcTemplate,
            final CategoryRowMapper categoryRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
    }

    public List<CategoryBean> listCategories(Integer limit, Integer offset) {
        final List<CategoryBean> categories = jdbcTemplate.query(
                "SELECT * FROM Category LIMIT ? OFFSET ?;",
                categoryRowMapper, limit, offset);

        return categories;
    }
}
