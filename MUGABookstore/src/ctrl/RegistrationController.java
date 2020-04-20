package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bean.UserBean;
import dao.UserDAO;
import helper.HelperLib;

@Controller
public class RegistrationController {

	@Autowired
	private UserDAO ud;

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public String showTerms() {
		return "terms";
	}

	@RequestMapping(value = "/privacy_policy", method = RequestMethod.GET)
	public String showPrivacyPolicy() {
		return "privacy_policy";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new UserBean());
		return mav;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserBean user) throws SQLException, Exception {

		ModelAndView mv = null;

		try {
			String filteredFirstName = HelperLib.xssPrevent(user.getFirstname());
			String filteredLastName = HelperLib.xssPrevent(user.getLastname());
			String filteredEmail = HelperLib.xssPrevent(user.getEmail());
			ud.registerUser(filteredFirstName, filteredLastName, filteredEmail, user.getPassword(),
					user.getType());
			
			mv = new ModelAndView("login", "firstname", filteredFirstName);
			mv.addObject("successMessage", "Account successfully created! Please login.");
			mv.addObject("accountCreated", true);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			mv = new ModelAndView("register");
			mv.addObject("errorMessage",
					"ERROR: There is already a MUGA Bookstore account associated with this email address. Please go to the Login page.");

		}
		return mv;
	}

}
