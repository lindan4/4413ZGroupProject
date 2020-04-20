package model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.AddressBean;
import bean.BookBean;
import bean.OrderBean;
import bean.ShoppingCartBean;
import dao.AddressDAO;
import dao.BookDAO;
import dao.OrderDAO;
import rx.OrderSubmittedEventPublisher;

@Component
public class OrderModel {

	@Autowired
	private OrderDAO oDao;

	@Autowired
	private AddressDAO aDao;

	@Autowired
	private BookDAO bDao;

	@Autowired
	private OrderSubmittedEventPublisher publisher;

	public void addAddress(AddressBean ab) {
		try {
			aDao.insertAddress(ab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getAddressId() throws SQLException {

		return aDao.getAddressId();

	}

	public List<OrderBean> getOrdersByBID(final String bid) {
		return oDao.getOrdersByBID(bid);
	}

	public void orderBook(String lastname, String firstname, int aid, Date date, String email) throws SQLException {

		try {
			oDao.addBookOrder(lastname, firstname, aid, date, email);
			this.publisher.publish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void orderBookItem(String bid, double price) throws SQLException {
//
//		try {
//			int o = oDao.getOrderId();
//			oDao.addBookOrderItem(bid, price);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void updateOrderStatuses(String email) throws SQLException {
		oDao.updateOrderStatuses(email);

	}

	public String getOrderStatusByOid(String oid) {
		return oDao.getOrderStatusByOid(oid);

	}

	public Date getOrderDate(String email) {
		return oDao.getOrderDate(email);
	}

	public List<OrderBean> getOrdersByEmail(String email) throws Exception {
		return oDao.getOrdersByEmail(email);
	}

	public ShoppingCartBean getPOItemsByOrderID(String oid) throws Exception {
		ShoppingCartBean sb = new ShoppingCartBean();
		List<?> poItems = oDao.getPOItemsByOrderID(oid);
		for (int i = 0; i < poItems.size(); i++) {
			Map<String, Object> itemMap = (Map<String, Object>) poItems.get(i);

			String bid = (String) itemMap.get("bid");
			int quantity = (int) itemMap.get("quantity");
			BookBean bb = bDao.getBookByID(bid);
			sb.getShoppingBean().put(bb, quantity);

		}
		return sb;

	}

}
