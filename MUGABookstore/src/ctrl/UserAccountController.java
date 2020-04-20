package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import bean.OrderBean;
import bean.UserBean;
import model.OrderModel;

@Controller

public class UserAccountController {

	@Autowired
	private OrderModel orderModel;

	@RequestMapping(value = "/user_account", method = RequestMethod.GET)
	public ModelAndView showUserAccount(@SessionAttribute("loggedInUser") UserBean u, HttpSession session, OrderBean ob)
			throws SQLException {
		ModelAndView mv = new ModelAndView("user_account");
		String statusOrder = "";

		orderModel.updateOrderStatuses(u.getEmail());


		return mv;

	}

}

/*
 * @RequestMapping(value="/user_account", method = RequestMethod.GET) public
 * ModelAndView showOrderStatus(String email, Date date) throws SQLException {
 * 
 * ModelAndView mv = new ModelAndView("user_account"); Date d = new Date(); Date
 * orderDate = orderModel.getOrderDate(email);
 * orderModel.updateOrderStatus(email, date);
 * 
 * if(d.after(orderDate)) { mv.addObject("status", "PROCESSED"); } else {
 * mv.addObject("status", "ORDERED");
 * 
 * } return mv;
 * 
 * }
 */
