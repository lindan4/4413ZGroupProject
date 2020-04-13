package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import bean.AddressBean;
import bean.BookBean;
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
		String query = "SELECT * FROM po";
		return jdbcTemplate.query(query, orderRowMapper);
	}

	public void addBookOrder(int oid, String lastname, String firstname, String status, int aid) throws SQLException {

		String query = "insert into po(id, lname, fname, STATUS, address) values(?,?,?,?,?)";
		jdbcTemplate.update(query, oid, lastname, firstname, status, aid);

	}

	public void addBookOrderItem(int oid, BookBean bb) throws SQLException{
		
		String bid = bb.getBid();
		double price = bb.getPrice();
		String query = "insert into poitem(id, bid, price) values(?,?,?)";
		jdbcTemplate.update(query, oid, bid, price);

	}
	
	/*
	public int getOrderId() throws SQLException {
		ResultSet rs;
		String query = "select max(id) as max_id from po";
		*/
		
		
	}


