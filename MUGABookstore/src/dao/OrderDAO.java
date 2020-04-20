package dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import bean.AddressBean;
import bean.BookBean;
import bean.BookReviewBean;
import bean.OrderBean;
import bean.ShoppingCartBean;
import bean.UserBean;
import mappers.OrderRowMapper;

@Component
public class OrderDAO {

	@Autowired
	private final JdbcTemplate jdbcTemplate;

	private final OrderRowMapper orderRowMapper;

	public OrderDAO(final JdbcTemplate jdbcTemplate, final OrderRowMapper orderRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.orderRowMapper = orderRowMapper;
	}

	public List<OrderBean> listOrders() throws Exception {
		String query = "SELECT * FROM PO";
		return jdbcTemplate.query(query, orderRowMapper);
	}
	
	public List<OrderBean> getOrdersByEmail(String email) throws Exception {
		String query = "SELECT * FROM PO WHERE email like ?";
		return jdbcTemplate.query(query, orderRowMapper, email);
		
	}

	public void addBookOrder(String lastname, String firstname, int aid, Date date, String email) throws SQLException {

		int id = this.getOrderId();
		String sOrdered = "ORDERED";
		String sDenied = "DENIED";
		// Date publishedDate = Date.valueOf(dateString);

		String query = "insert into PO(id, lname, fname, STATUS, address, date, email) values(?,?,?,?,?,?,?)";
		if (id % 3 == 0) {
			jdbcTemplate.update(query, id, lastname, firstname, sDenied, aid, date, email);

		} else {
			jdbcTemplate.update(query, id, lastname, firstname, sOrdered, aid, date, email);
		}
	}

	public void addBookOrderItem(String bid, double price, int quantity) throws SQLException {

		int o = (this.getOrderId() - 1);
		String query = "INSERT IGNORE INTO POItem(id, bid, price, quantity) VALUES(?,?,?,?)";
		jdbcTemplate.update(query, o, bid, price, quantity);

	}

	public int getOrderId() throws SQLException {

		String query = "select max(id) from PO";
		int id = jdbcTemplate.queryForObject(query, Integer.class);
		return (id + 1);
	}

	public int getItemOrderId() throws SQLException {

		String query = "select max(id) from POItem";
		int id = jdbcTemplate.queryForObject(query, Integer.class);
		return (id + 1);
	}

	public List<OrderBean> getOrdersByBID(final String bid) {
		final String query = "SELECT * from PO as po \n" +
				"INNER JOIN (SELECT * FROM POItem WHERE bid = ?) as poItem\n" +
				"ON poItem.id = po.id;";

		return jdbcTemplate.query(query, orderRowMapper, bid);
	}
	
	public String getOrderStatusByOid(String oid) {
		String query = "SELECT STATUS FROM PO WHERE id = ?";
		List<?> result = jdbcTemplate.queryForList(query,  oid);
		Map<String, Object> ms = (Map<String, Object>) result.get(0);
		String status = (String) ms.get("STATUS");
		return status;
		
	}

	public void updateOrderStatuses(final String email) throws SQLException {
		
		String fetchQuery = "SELECT * FROM PO WHERE email = ? AND status = ?";
		List<?> orders = jdbcTemplate.queryForList(fetchQuery, email, "ORDERED");
		Date today = new Date();
		
		
		String updateQuery = "UPDATE PO SET status='PROCESSED' WHERE email = ? AND id = ?";
		for (int i = 0; i < orders.size(); i++) {
			Map<String, Object> mp = (Map<String, Object>) orders.get(i);
			Date date = (Date) mp.get("Date");
			long id = (long) mp.get("id");
			if (today.after(date)) {
				jdbcTemplate.update(updateQuery, email, id);

			}

		}

	}


	public Date getOrderDate(String email) {
		String currentDate = "SELECT date FROM PO WHERE email = ?";
		try {
			Date results = (Date) jdbcTemplate.queryForObject(currentDate, new Object[] { email }, Date.class);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<?> getPOItemsByOrderID(String oid) throws Exception {
		String query = "SELECT bid, price, quantity FROM POITEM WHERE id = ?";
		return jdbcTemplate.queryForList(query, oid);
		
	}


}
