package ctrl;

import bean.BookBean;
import bean.CategoryBean;
import model.BookModel;
import model.CategoryModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductCatalogueController {
    private final BookModel bookModel;

    private final CategoryModel  categoryModel;

    ProductCatalogueController(
            final BookModel bookModel,
            final CategoryModel categoryModel
    ) {
        this.categoryModel = categoryModel;
        this.bookModel = bookModel;
    }

    @RequestMapping(value = "/product-catalogue", method = RequestMethod.GET)
    public ModelAndView getPage(@RequestParam(required = false) String category) throws Exception {
        final List<BookBean> books = category != null ?
                this.bookModel.listBooks(25, category) :
                this.bookModel.listBooks(25);

        final List<CategoryBean> categories = this.categoryModel.listCategories(100, 0);

        final ModelAndView mv = new ModelAndView("product_catalogue");
        mv.addObject("books", books);
        mv.addObject("categories", categories);

        return mv;
    }

    @RequestMapping(value = "/rest/products", method = RequestMethod.GET)
    public Object getProductInfo(@RequestParam String bid) {
        final BookBean book = this.bookModel.getBookByID(bid);

        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
