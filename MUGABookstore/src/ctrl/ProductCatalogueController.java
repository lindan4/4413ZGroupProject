package ctrl;

import bean.BookBean;
import model.BookModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductCatalogueController {
    private final BookModel bookModel;

    ProductCatalogueController(final BookModel bookModel) {
        this.bookModel = bookModel;
    }

    @RequestMapping(value = "/product-catalogue", method = RequestMethod.GET)
    public ModelAndView getPage( ) throws Exception {
        final List<BookBean> books = this.bookModel.listBooks();

        final ModelAndView mv = new ModelAndView("product_catalogue");
        mv.addObject("books", books);

        return mv;
    }
}
