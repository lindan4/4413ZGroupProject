package ctrl;

import java.util.List;

import bean.OrderBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import model.OrderModel;

@Controller
public class OrderController {


	private final OrderModel orderModel;

	public OrderController(OrderModel orderModel) {
		this.orderModel = orderModel;
	}


	@RequestMapping(value = "/orders/{bid}", method = RequestMethod.GET)
	public Object getOrdersByBid(@PathVariable("bid") String bid) {
		final List<OrderBean> orders = this.orderModel.getOrdersByBID(bid);


		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

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
