package ctrl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bean.AddressBean;
import bean.ShoppingCartBean;
import bean.UserBean;
import helper.HelperLib;
import model.OrderModel;
import model.ShoppingCartModel;
import model.UserModel;

@Controller
@SessionAttributes({ "addressBean", "userBean", "shoppingCart" })
public class PaymentController {
	@Autowired
	private ShoppingCartModel shoppingCartModel;

	@Autowired
	private UserModel userModel;

	@Autowired
	private OrderModel orderModel;

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
		} else {
			mv.addObject("hideUserCreate", false);
		}
		return mv;

	}

	@RequestMapping(value = "/submitBillInfo", params = "cancelBillingPost", method = RequestMethod.POST)
	public String returnToShoppingCart() {
		return "redirect:/cart";
	}

	@RequestMapping(value = "/submitBillInfo", params = "submitBillingPost", method = RequestMethod.POST)
	public ModelAndView goToConfirmation(@SessionAttribute("shoppingCart") ShoppingCartBean sb,
			@RequestParam Map<String, String> allRequestParams, AddressBean a, HttpSession session) throws SQLException, Exception {

		ModelAndView mv = new ModelAndView("payment_order_confirmation");
		double totalSbPrice = shoppingCartModel.calculateTotal(sb);
		String fName = HelperLib.xssPrevent(allRequestParams.get("orderFirstName"));
		String lName = HelperLib.xssPrevent(allRequestParams.get("orderLastName"));
		String email = HelperLib.xssPrevent(allRequestParams.get("email"));
		String password = allRequestParams.get("password");
		String shippingAddress = HelperLib.xssPrevent(allRequestParams.get("shippingAddress"));
		String shippingCity = HelperLib.xssPrevent(allRequestParams.get("shippingCity"));
		String shipProv = HelperLib.xssPrevent(allRequestParams.get("shippingState/Province"));
		String shipCountry = HelperLib.xssPrevent(allRequestParams.get("shippingCountry"));
		String shipPostOrZip = HelperLib.xssPrevent(allRequestParams.get("shippingPostal/zipcode"));
		String shipPhoneNo = HelperLib.xssPrevent(allRequestParams.get("shippingPhoneNo"));
		AddressBean ab = new AddressBean(orderModel.getAddressId(), shippingAddress, shippingCity, shipProv,
				shipCountry, shipPostOrZip, shipPhoneNo);
		UserBean ub = new UserBean(fName, lName, email, password, "customer");

		if (session.getAttribute("loggedInUser") == null) {
			try {

				userModel.registerUser(ub);

			} catch (DuplicateKeyException e) {
				mv = new ModelAndView("payment_billing_info");
				mv.addObject("hideUserCreate", false);
				mv.addObject("errorMessage",
	"ERROR: There is already a MUGA Bookstore account associated with this email address. Please go to the Login page.");
			}
		}

		mv.addObject("sbTotal", totalSbPrice);
		mv.addObject("shoppingCart", sb);
		mv.addObject("addressBean", ab);
		mv.addObject("userBean", ub);
		return mv;
	}

	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public ModelAndView placeOrder(@SessionAttribute("shoppingCart") ShoppingCartBean sb,
			@SessionAttribute("userBean") UserBean ub, @SessionAttribute("addressBean") AddressBean ab)
			throws SQLException {

		ModelAndView mv = new ModelAndView("order_complete");
		double totalSbPrice = shoppingCartModel.calculateTotal(sb);
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		String fName = ub.getFirstname();
		String lName = ub.getLastname();
		String email = ub.getEmail();
		int sid = ab.getId();
		int newSid = sid + 1;
		Date date = new Date();
		orderModel.addAddress(ab);
		orderModel.orderBook(lName, fName, sid, date, email);
		// Insert into poitem
		boolean transactionApproved;
		shoppingCartModel.insertWithBID(sb);
		if (newSid % 3 == 0) {
			transactionApproved = false;
		}
		else {
			transactionApproved = true;
		}
		mv.addObject("trApp", transactionApproved);
		
		mv.addObject("shoppingCart", new ShoppingCartBean());

		return mv;

	}

	@RequestMapping(value = "/signInUser", method = RequestMethod.POST)
	public ModelAndView signIn(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @SessionAttribute("shoppingCart") ShoppingCartBean sb,
			HttpSession session) throws SQLException, Exception {

		ModelAndView mv = new ModelAndView("payment_account_type");
		UserBean user = userModel.getUserByEmail(email, password);

		if (user == null) {
			mv.addObject("loginError", "The email or password is incorrect! Please try again.");
			// mv = new ModelAndView("payment_account_type");
			return mv;
		}

		session.setAttribute("loggedInUser", user);

		return this.billingInfo(sb, session);
	}

}
