package bean;

import java.util.Map;
import java.util.TreeMap;

public class ShoppingCartBean {
	
	private Map<BookBean, Integer> shoppingBean;

	public ShoppingCartBean() {
		this.setsBean(new TreeMap<BookBean, Integer>());
	}

	public Map<BookBean, Integer> getShoppingBean() {
		return shoppingBean;
	}

	public void setsBean(TreeMap<BookBean, Integer> hashMap) {
		this.shoppingBean = hashMap;
	}
	


}
