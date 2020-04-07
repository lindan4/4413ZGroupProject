package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		// session.setAttribute("loggedInUser", user);
		this.addUserInSession(user, session);
		return "redirect:/";

	}

	private void addUserInSession(UserBean u, HttpSession session) {
		session.setAttribute("loggedInUser", u);
		session.setAttribute("userFirstname", u.getFirstname());
		session.setAttribute("userEmail", u.getEmail());

	}

}
