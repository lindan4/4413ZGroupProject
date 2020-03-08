package ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bean.BookBean;
import bean.BookReviewBean;
import bean.ShoppingCartBean;
import model.BookModel;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class HomeController {

	@Autowired
	private BookModel bookModel;


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

		if (!searchQuery.isEmpty()) {
			try {
				LinkedList<BookBean> bb = bookModel.retrieveBookQuery(searchQuery);
				mv.addObject("queryResults", bb);
				mv.addObject("queryResultCount", bb.size());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			mv.addObject("queryResultCount", 0);
		}
				
		
		return mv;
	}
	
	@RequestMapping(value = "/bookinfo", method = RequestMethod.GET)
	public ModelAndView getBookInfo(@RequestParam String bid) {
		ModelAndView bookMV = new ModelAndView("book_info");
		try {
			BookBean bbSingle = bookModel.getBookByID(bid);
			LinkedList<BookReviewBean> bbr = bookModel.retrieveBookReviews(bid);
			bookMV.addObject("bookInformation", bbSingle);
			bookMV.addObject("bookReviews", bbr);
			bookMV.addObject("bookReviewCount", bbr.size());


		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookMV;
	}
	@RequestMapping(value = "/submitReview", method = RequestMethod.POST)
	public String submitReview(@RequestParam String submitBid, @RequestParam String reviewInputContent, @RequestParam int star) {
		
		
		try {
			Date date = new Date(System.currentTimeMillis());
			String formattedDate = date.toString();
			
			//"Unknown is a placeholder until we can get accounts working."
			bookModel.publishReview(submitBid, "Unknown", star, reviewInputContent, formattedDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/bookinfo?bid=" + submitBid;
	}
	
	
	
	
}