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

import mappers.BookRowMapper;
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

    private final BookRowMapper bookRowMapper;

    public BookDAO(final JdbcTemplate jdbcTemplate, final BookRowMapper bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    public List<BookBean> listBooks() throws Exception {
        final String query = "SELECT * FROM Book";
        return jdbcTemplate.query(query, bookRowMapper);
    }

	public LinkedList<BookBean> retrieveBookQuery(String queryInput) throws Exception, SQLException {
		
		LinkedList<BookBean> resultBean = new LinkedList<BookBean>();

		String query = "select * from Book where title like ?";
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
		String query = "select * from Book where bid=?";
		
		List<?> results = jdbcTemplate.queryForList(query, bid);
		Map<String, Object> itemMap = (Map<String, Object>) results.get(0);
		
		String title = (String) itemMap.get("title");
		int price = (int) itemMap.get("price");
		String category = (String) itemMap.get("category");
		
		return new BookBean(bid, title, price, category);
		
	}

}
