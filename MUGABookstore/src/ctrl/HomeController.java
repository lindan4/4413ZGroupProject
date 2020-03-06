package ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bean.BookBean;
import model.BookModel;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class HomeController {

	private final BookModel bookModel;

	public HomeController(BookModel bookModel) {
		this.bookModel = bookModel;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView returnHome() {
//		String query = "select * from book";
//		
//		List<?> results = jdbcTemplate.queryForList(query);
//		System.out.println(results.toString());
		
		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/searchQuery", method = RequestMethod.POST)
	public ModelAndView returnSearchResults(@RequestParam String searchQuery) {
		ModelAndView mv = new ModelAndView("searchQueryResults");

		
		try {
			LinkedList<BookBean> bb = bookModel.retrieveBookQuery(searchQuery);
			mv.addObject(mv);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(mv);
		
		
		return mv;
	}
	
}