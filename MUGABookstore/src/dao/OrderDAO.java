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

	public void addBookOrderItem(String bid, double price) throws SQLException {

		int o = (this.getOrderId() - 1);
		String query = "insert into POItem(id, bid, price) values(?,?,?)";
		jdbcTemplate.update(query, o, bid, price);

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

	public Date updateOrderStatus(String email, Date d) throws SQLException {

		Date today = new Date();

		String update = "UPDATE PO SET status='PROCESSED' WHERE email=?";
		String currentDate = "SELECT date FROM PO WHERE email=?";
		Date result = jdbcTemplate.queryForObject(currentDate, new String[] { email }, Date.class);

		if (today.after(result)) {
			jdbcTemplate.update(update, email);
		}
		return result;

	}


	public Date getOrderDate(String email) {
		String currentDate = "SELECT date FROM PO WHERE email=?";
		try {
			Date results = (Date) jdbcTemplate.queryForObject(currentDate, new Object[] { email }, Date.class);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
