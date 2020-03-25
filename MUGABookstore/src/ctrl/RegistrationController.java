package ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.UserBean;
import dao.UserDAO;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserDAO ud;
	

	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("register");
	    mav.addObject("user", new UserBean());
	    return mav;
	  }
	  

	  @RequestMapping(value = "/home", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("user") UserBean user) {
	  ud.registerUser(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getType());
	  return new ModelAndView("login", "firstname", user.getFirstname());
	  }
	}
	  



