package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bean.CategoryBean;
import mappers.CategoryRowMapper;

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
                "SELECT * FROM Category ORDER BY category_id DESC LIMIT ? OFFSET ?;",
                categoryRowMapper, limit, offset);

        return categories;
    }
}
