package ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import bean.ShoppingCartBean;
import bean.UserBean;
import model.ShoppingCartModel;

@Controller
public class PaymentController {
	@Autowired
	private ShoppingCartModel shoppingCartModel;

	@RequestMapping(value = "/processCart", params = "checkoutCart", method = RequestMethod.POST)
	public ModelAndView checkoutSelected(HttpSession session, @SessionAttribute("shoppingCart") ShoppingCartBean sb) {

		if (session.getAttribute("loggedInUser") != null) {
			return this.billingInfo(sb, session);

		} else {
			return new ModelAndView("payment_account_type");
		}

	}

	@RequestMapping(value = "/orderBillingInfo", method = RequestMethod.POST)
	public ModelAndView billingInfo(@SessionAttribute("shoppingCart") ShoppingCartBean sb, HttpSession session) {
		double totalSbPrice = shoppingCartModel.calculateTotal(sb);
		ModelAndView mv = new ModelAndView("payment_billing_info");
		mv.addObject("sbTotal", totalSbPrice);
		mv.addObject("sbCart", sb);
		if (session.getAttribute("loggedInUser") != null) {
			mv.addObject("hideUserCreate", true);
		}
		else {
			mv.addObject("hideUserCreate", false);
		}
		return mv;

	}

	@RequestMapping(value = "/submitBillInfo", params = "cancelBillingPost", method = RequestMethod.POST)
	public String returnToShoppingCart() {
		return "redirect:/cart";
	}

	@RequestMapping(value = "/submitBillInfo", params = "submitBillingPost", method = RequestMethod.POST)
	public ModelAndView goToConfirmation() {
		ModelAndView mv = new ModelAndView("payment_order_confirmation");
		return mv;
	}

}
