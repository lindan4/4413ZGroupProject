package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
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

	public void addBookOrder(String lastname, String firstname, int aid) throws SQLException {

		int id = this.getOrderId();
		String sOrdered = "ORDERED";
		String sDenied = "DENIED";

		String query = "insert into PO(id, lname, fname, STATUS, address) values(?,?,?,?,?)";
		if (id % 3 == 0) {
			jdbcTemplate.update(query, id, lastname, firstname, sDenied, aid);

		} else {
			jdbcTemplate.update(query, id, lastname, firstname, sOrdered, aid);
		}
	}

	public void addBookOrderItem(String bid, double price) throws SQLException {

		int o = (this.getOrderId()-1);
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

}
