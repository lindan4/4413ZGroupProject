package model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.AddressBean;
import bean.OrderBean;
import bean.ShoppingCartBean;
import dao.AddressDAO;
import dao.OrderDAO;

@Component
public class OrderModel {

	@Autowired
	private OrderDAO oDao;
	
	@Autowired
	private AddressDAO aDao;
	
	public void addAddress(AddressBean ab) {
		try {
			aDao.insertAddress(ab);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getAddressId() throws SQLException {
		
	return aDao.getAddressId();
		
	}

	public void orderBook(String lastname, String firstname, String status, int aid) throws SQLException {

		try {
			oDao.addBookOrder(lastname, firstname, status, aid);
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
	
	
	
	
}
