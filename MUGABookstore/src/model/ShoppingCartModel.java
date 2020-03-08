package model;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import bean.BookBean;
import bean.ShoppingCartBean;
import dao.BookDAO;

@Component
public class ShoppingCartModel {
	
	@Autowired
	private BookDAO bDao;
	
	public void addToShoppingCart(String bid, ShoppingCartBean sb) throws SQLException, Exception {
		BookBean bb = bDao.getBookByID(bid);
		//If the user has an account, then they must have a record in the database, add it there
		
		if (sb.getShoppingBean().containsKey(bb)) {
			//Get item
			//Increase count;
			int count = sb.getShoppingBean().get(bb) + 1;
			sb.getShoppingBean().put(bb, count);
		}
		else {
			sb.getShoppingBean().put(bb, 1);
		}
		
		
	}
	

}
