package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.BookBean;
import bean.ShoppingCartBean;
import dao.BookDAO;
import dao.OrderDAO;

@Component
public class ShoppingCartModel {

	@Autowired
	private BookDAO bDao;

	@Autowired
	private OrderDAO oDao;

	//Add item to shopping cart
	public void addToShoppingCart(String bid, ShoppingCartBean sb) throws SQLException, Exception {
		BookBean bb = bDao.getBookByID(bid);
		

		if (sb.getShoppingBean().containsKey(bb)) {
			// Get item
			// Increase count;
			int count = sb.getShoppingBean().get(bb) + 1;
			sb.getShoppingBean().put(bb, count);
		} else {
			sb.getShoppingBean().put(bb, 1);
		}
	}

	public void updateShoppingCart(Map<String, String> updateQt, ShoppingCartBean sb) throws SQLException, Exception {
		// If user has account, then also update in database
		// Depending on the button pressed in the shopping cart page (UPDATE or PAYMENT), eacn of them emit a value that
		// we check for under the "containsKey" invocations and execute a method depending on the operation requested
		if (updateQt.containsKey("updateCart")) {
			for (String bookId : updateQt.keySet()) {
				if (!bookId.equals("updateCart")) {
					BookBean bb = bDao.getBookByID(bookId);
					int qty = Integer.parseInt(updateQt.get(bookId));
					if (qty <= 0) {
						sb.getShoppingBean().remove(bb);

					} else {
						sb.getShoppingBean().put(bb, Integer.parseInt(updateQt.get(bookId)));

					}
				}

			}
		}
		if (updateQt.containsKey("removeBookFromCart")) {
			BookBean bb = bDao.getBookByID(updateQt.get("removeBookFromCart"));
			sb.getShoppingBean().remove(bb);

		}

	}

	//Calculate shopping cart total
	public double calculateTotal(ShoppingCartBean sb) {
		double total = 0;

		for (BookBean bb : sb.getShoppingBean().keySet()) {
			total = total + bb.getPrice() * sb.getShoppingBean().get(bb);

		}
		return total;
	}

	//Insert POItems from shopping cart once purchase is complete
	public void insertWithBID(ShoppingCartBean sb) throws SQLException {
		List<String> s = new ArrayList<>();
		//BookBean bb = bDao.getBookByID(bid);

		
		for (BookBean bb : sb.getShoppingBean().keySet()) {
				int quantity = sb.getShoppingBean().get(bb);
				oDao.addBookOrderItem(bb.getBid(), bb.getPrice(), quantity);
		}
			
	}

}
