package dao;

import java.sql.SQLException;

import bean.BookBean;
import bean.BookReviewBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookReviewDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public LinkedList<BookReviewBean> retrieveBookReviews(String bid) throws SQLException {
		LinkedList<BookReviewBean> brb = new LinkedList<BookReviewBean>();
		
		
		String query = "select * from bookreview where bid=?";
		
		List<?> results = jdbcTemplate.queryForList(query, bid);
		for (int i = 0; i < results.size(); i++) {
			Map<String, Object> itemMap = (Map<String, Object>) results.get(i);
			String reviewerName = (String) itemMap.get("reviewer_name");
			int rating = (int) itemMap.get("rating");
			String content = (String) itemMap.get("content");
			brb.add(new BookReviewBean(bid, reviewerName, rating, content));
		}

		return brb;
	}

}
