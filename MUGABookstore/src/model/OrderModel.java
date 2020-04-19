package model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.AddressBean;
import bean.OrderBean;
import bean.ShoppingCartBean;
import dao.AddressDAO;
import dao.OrderDAO;
import rx.OrderSubmittedEventPublisher;

@Component
public class OrderModel {

	@Autowired
	private OrderDAO oDao;

	@Autowired
	private AddressDAO aDao;

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

	public void orderBookItem(String bid, double price) throws SQLException {

		try {
			int o = oDao.getOrderId();
			oDao.addBookOrderItem(bid, price);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Date updateOrderStatus(String email, Date date) throws SQLException
	{
		return oDao.updateOrderStatus(email, date);
		
	}
	
	public Date getOrderDate(String email) {
		return oDao.getOrderDate(email);
	}
	
	
	

}
