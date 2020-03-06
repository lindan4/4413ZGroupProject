package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bean.BookBean;


@Component
public class BookDAO {
	
	private final JdbcTemplate jdbcTemplate;
	
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public LinkedList<BookBean> retrieveBookQuery(String queryInput) throws Exception, SQLException {
		
		LinkedList<BookBean> resultBean = new LinkedList<BookBean>();

		String query = "select * from book where title like ?";
		List<?> results = jdbcTemplate.queryForList(query, "%"+ queryInput + "%");
		for (int i = 0; i < results.size(); i++) {
			Map<String, Object> itemMap = (Map<String, Object>) results.get(i);
			String bid = (String) itemMap.get("bid");
			String title = (String) itemMap.get("title");
			int price = (int) itemMap.get("price");
			String category = (String) itemMap.get("category");
			resultBean.add(new BookBean(bid, title, price, category));
		}
		
		return resultBean;
	}
	
	public BookBean getBookByID(String bid) throws Exception, SQLException {
		String query = "select * from book where bid=?";
		
		List<?> results = jdbcTemplate.queryForList(query, bid);
		Map<String, Object> itemMap = (Map<String, Object>) results.get(0);
		
		String title = (String) itemMap.get("title");
		int price = (int) itemMap.get("price");
		String category = (String) itemMap.get("category");
		
		return new BookBean(bid, title, price, category);
		
	}

}
