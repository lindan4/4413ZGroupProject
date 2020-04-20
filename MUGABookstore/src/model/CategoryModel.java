package model;

import java.util.List;

import org.springframework.stereotype.Component;

import bean.CategoryBean;
import dao.CategoryDAO;

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
