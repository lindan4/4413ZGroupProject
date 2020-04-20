package ctrl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bean.BookBean;
import bean.CategoryBean;
import model.BookModel;
import model.CategoryModel;

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

    @RequestMapping(value = "/product-catalogue/{bid}", method = RequestMethod.GET)
    public Object getProductInfo(@PathVariable("bid") String bid) {
        final BookBean book = this.bookModel.getBookByID(bid);

        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
