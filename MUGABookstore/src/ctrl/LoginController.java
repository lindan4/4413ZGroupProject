package ctrl;

import java.sql.SQLException;

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
public class LoginController {
	
	public static final String LOGIN_STATUS = "login_status";
	
	@Autowired
	private UserDAO ud;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("login", new UserBean());
		return mv;
		
	}
	
	@RequestMapping(value = "/loginProcess", method=RequestMethod.POST)
	public ModelAndView loginUser(Model model,  @RequestParam("email") String email, @RequestParam("password") String password) throws SQLException, Exception {
		
		ModelAndView mv = null;
		
		UserBean ub = ud.getUserByEmail(email);
		
		
		if(ub != null) {
			mv = new ModelAndView("home");
			//mv.addObject("firstnameLogin", ub.getFirstname());
		}
		else {
			mv = new ModelAndView("login");
			mv.addObject("Message", "Incorrect Credentials!");
		}
		
		return mv;
	}
	
	
	

	
}
