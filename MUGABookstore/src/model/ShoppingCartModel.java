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

	public void addToShoppingCart(String bid, ShoppingCartBean sb) throws SQLException, Exception {
		BookBean bb = bDao.getBookByID(bid);
		// If the user has an account, then they must have a record in the database, add
		// it there

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

	public double calculateTotal(ShoppingCartBean sb) {
		double total = 0;

		for (BookBean bb : sb.getShoppingBean().keySet()) {
			total = total + bb.getPrice() * sb.getShoppingBean().get(bb);

		}
		return total;
	}

	public void insertWithBID(ShoppingCartBean sb) throws SQLException {
		List<String> s = new ArrayList<>();
		//BookBean bb = bDao.getBookByID(bid);

		
		for (BookBean bb : sb.getShoppingBean().keySet()) {
				int quantity = sb.getShoppingBean().get(bb);
				oDao.addBookOrderItem(bb.getBid(), bb.getPrice(), quantity);
		}
			
	}

}
