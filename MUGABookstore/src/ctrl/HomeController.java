package ctrl;

import java.sql.Date;
import java.util.LinkedList;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.BookBean;
import bean.BookReviewBean;
import bean.UserBean;
import helper.HelperLib;
import model.BookModel;


@Controller
public class HomeController {

	@Autowired
	private BookModel bookModel;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView returnHome() {

		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/searchQuery", method = RequestMethod.POST)
	public ModelAndView returnSearchResults(@RequestParam String searchQuery) {
		
		ModelAndView mv = new ModelAndView("searchQueryResults");

		if (!searchQuery.isEmpty()) {
			try {
				String filteredSearchQuery = HelperLib.xssPrevent(searchQuery);
				
				//Search query is invoked from here
				TreeMap<String,BookBean> bb = bookModel.retrieveBookQuery(filteredSearchQuery);
				mv.addObject("queryResults", bb.values());
				mv.addObject("queryResultCount", bb.size());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ModelAndView mvError = new ModelAndView("error");
				return mvError;
			}
		}
		else {
			mv.addObject("queryResultCount", 0);
		}
				
		
		return mv;
	}
	
	@RequestMapping(value = "/bookinfo", method = RequestMethod.GET)
	public ModelAndView getBookInfo(@RequestParam String bid) {
		ModelAndView mv = new ModelAndView("book_info");
		try {
			BookBean book = bookModel.getBookByID(bid);
			LinkedList<BookReviewBean> bookReviews = bookModel.retrieveBookReviews(bid);
			mv.addObject("bookInformation", book);
			mv.addObject("bookReviews", bookReviews);
			mv.addObject("bookReviewCount", bookReviews.size());


		} 
		catch (Exception e) {
			ModelAndView mvError = new ModelAndView("error");
			return mvError;
		}
		return mv;
	}
	@RequestMapping(value = "/submitReview", method = RequestMethod.POST)
	public String submitReview(@RequestParam String submitBid, @RequestParam String reviewInputContent, @RequestParam int star, HttpSession session) {
		
		
		try {
			Date date = new Date(System.currentTimeMillis());
			String formattedDate = date.toString();
			String namePlaceholder = "Unknown";
			
			//If a user is logged in while posting a review, then get their first name and use it
			if(session.getAttribute("loggedInUser") != null) {
				UserBean ub = (UserBean) session.getAttribute("loggedInUser");
				namePlaceholder = ub.getFirstname();
				
			}
			
			
			String filteredContent = HelperLib.xssPrevent(reviewInputContent);
			bookModel.publishReview(submitBid, namePlaceholder, star, filteredContent, formattedDate);
		} catch (Exception e) {
			return "error";
		}
		
		return "redirect:/bookinfo?bid=" + submitBid;
	}
	
	
	
	
}