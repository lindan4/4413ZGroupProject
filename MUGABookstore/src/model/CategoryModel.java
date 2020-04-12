package model;

import bean.CategoryBean;
import dao.CategoryDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryModel {
    private final CategoryDAO categoryDAO;

    public CategoryModel(
            final CategoryDAO categoryDAO
    ) {
        this.categoryDAO = categoryDAO;
    }

    public List<CategoryBean> listCategories(Integer limit, Integer offset) {
        return this.categoryDAO.listCategories(limit, offset);
    }
}
