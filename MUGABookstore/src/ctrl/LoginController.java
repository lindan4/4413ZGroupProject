package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bean.UserBean;
import model.UserModel;

@Controller
public class LoginController {

	public static final String LOGIN_STATUS = "login_status";
	public static final String USER_ROLE = "role";

	@Autowired
	private UserModel userModel;

	/*
	 * @RequestMapping(value = "/login")//, method = RequestMethod.GET) public
	 * ModelAndView displayLogin() {//HttpServletRequest request,
	 * HttpServletResponse response, Model m) { ModelAndView mv = new
	 * ModelAndView("login"); //mv.addObject("login", new UserBean());
	 * //m.addAttribute("m", m); return mv;
	 * 
	 * }
	 * 
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogin() {
		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, HttpSession session, Model m)
			throws SQLException, Exception {

		UserBean user = userModel.getUserByEmail(email, password);

		if (user == null) {
			m.addAttribute("loginError", "The email or password is incorrect! Please try again.");
			return "login";
		}

		this.addUserInSession(user, session);
		return "redirect:/";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		session.invalidate();

		return "redirect:/";
	}

	private void addUserInSession(UserBean u, HttpSession session) {
		session.setAttribute("loggedInUser", u);
		session.setAttribute("userFirstname", u.getFirstname());
		session.setAttribute("userEmail", u.getEmail());
		session.setAttribute("userType", u.getType());

	}

}
