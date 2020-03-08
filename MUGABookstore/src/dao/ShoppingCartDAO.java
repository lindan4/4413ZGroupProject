package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bean.ShoppingCartBean;

@Component
public class ShoppingCartDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addToShoppingCart(String bid, ShoppingCartBean sb) {
		
		
		
	}

}
