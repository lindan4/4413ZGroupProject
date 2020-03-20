package ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import bean.ShoppingCartBean;
import model.ShoppingCartModel;

@Controller
public class PaymentController {
	
	@RequestMapping(value = "/processCart", params = "checkoutCart", method = RequestMethod.POST)
	public String checkoutSelected() {
		return "payment_account_type";
	}


}
