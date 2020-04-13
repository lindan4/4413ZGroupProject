package ctrl;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import bean.ShoppingCartBean;
import model.OrderModel;

@Controller
public class OrderController {


	private OrderModel orderModel;
	
	/*
	@RequestMapping(value="/orderComplete", method=RequestMethod.GET)
	public String showOrderDetails() {
		return "order_complete";
		
	}
	*/
	/*
	@RequestMapping(value="/orderComplete", method=RequestMethod.POST)
	public ModelAndView placeOrder(@SessionAttribute("shoppingCart") ShoppingCartBean sb,
			@RequestParam Map<String, String> allRequestParams, HttpSession session) throws SQLException {
		
		ModelAndView mv = new ModelAndView("order_complete");
		//String fName = allRequestParams.get("orderFirstName");
		//String lName = allRequestParams.get("orderLastName");
		//String email = allRequestParams.get("email");
		//String shippingAddress = allRequestParams.get("shippingAddress");
		
		orderModel.orderBook("4", "smith", "alex", "ORDERED", "4700 Keele Street");
		
		return mv;

	}
	*/
	
}
