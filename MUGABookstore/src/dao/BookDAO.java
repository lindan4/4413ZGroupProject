package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public BookDAO() {
	}

	public LinkedList<BookBean> retrieveBookQuery(String queryInput) throws Exception, SQLException {
		String query = "select * from book where title like %?%";
		List<?> results = jdbcTemplate.queryForList(query, queryInput);
		System.out.println(results.toString());
		
		LinkedList<BookBean> resultBean = new LinkedList<BookBean>();
		
		
		
		return resultBean;
	}

}
