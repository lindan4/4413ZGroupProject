package ctrl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bean.BookBean;
import bean.ShoppingCartBean;
import model.ShoppingCartModel;

@Controller
@SessionAttributes("shoppingCart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartModel shoppingCartModel;
	
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public ModelAndView showCartContent(@ModelAttribute("shoppingCart") ShoppingCartBean shoppingCart) {
		ModelAndView mv = new ModelAndView("shopping_cart");
		mv.addObject("shoppingCart", shoppingCart);
		mv.addObject("shoppingCartCount", shoppingCart.getShoppingBean().size());
		mv.addObject("cartCost", shoppingCartModel.calculateTotal(shoppingCart));
		
		
		return mv;
		
	}
	
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(Model model, @RequestParam String bid, @ModelAttribute("shoppingCart") ShoppingCartBean shoppingCart) throws SQLException, Exception {
		
		shoppingCartModel.addToShoppingCart(bid, shoppingCart);

		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/processCart", method = RequestMethod.POST)
	public String updateCartValue(Model model, @RequestParam Map<String, String> booksToUpdate, @ModelAttribute("shoppingCart") ShoppingCartBean shoppingCart) throws SQLException, Exception {
		shoppingCartModel.updateShoppingCart(booksToUpdate, shoppingCart);
		return "redirect:/cart";
	}
	
	//Feel iffy about placing this here. Might change.
	@ModelAttribute("shoppingCart")
	public ShoppingCartBean getShoppingCartBean() {
		return new ShoppingCartBean();
	}
	
}