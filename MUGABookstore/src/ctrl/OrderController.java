package ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.OrderBean;
import bean.ShoppingCartBean;
import bean.UserBean;
import model.OrderModel;
import model.ShoppingCartModel;

@Controller
public class OrderController {


	@Autowired
	private final OrderModel orderModel;
	
	@Autowired
	private ShoppingCartModel sModel;

	public OrderController(OrderModel orderModel) {
		this.orderModel = orderModel;
	}


	@RequestMapping(value = "/rest/orders", method = RequestMethod.GET)
	public Object getOrdersByBid(@RequestParam() String bid) {
		final List<OrderBean> orders = this.orderModel.getOrdersByBID(bid);


		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView viewOrders(HttpSession session) throws Exception {
		if (session.getAttribute("loggedInUser") == null) {
			return new ModelAndView("redirect:/login");
			
		}
		else {
			UserBean ub = (UserBean) session.getAttribute("loggedInUser"); 
			ModelAndView mv = new ModelAndView("account_orders");
			List<OrderBean> obList = orderModel.getOrdersByEmail(ub.getEmail());
			mv.addObject("listUserOrders", obList);
			return mv;
			
			
		}
		
	}
	
	@RequestMapping(value = "/viewOrderInformation", method = RequestMethod.GET)
	public ModelAndView viewOrderInformation(@RequestParam("viewOrder") String oid, HttpSession session) throws Exception {
		try {
			if (session.getAttribute("loggedInUser") == null) {
				return new ModelAndView("redirect:/login");
				
			}
			else {
				ShoppingCartBean sb = orderModel.getPOItemsByOrderID(oid);
				double price = sModel.calculateTotal(sb);
				String orderStatus = orderModel.getOrderStatusByOid(oid);
				ModelAndView mv = new ModelAndView("account_order_details");
				mv.addObject("oid", oid);
				mv.addObject("orderItemList", sb);
				mv.addObject("orderStatus", orderStatus);
				mv.addObject("orderPrice", price);
				return mv;
			}
		}
		catch (Exception e) {
			return new ModelAndView("error");

		}
	}
	
}
