package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
		
		
		String query = "select * from BookReview where bid=?";
		
		List<?> results = jdbcTemplate.queryForList(query, bid);
		for (int i = 0; i < results.size(); i++) {
			Map<String, Object> itemMap = (Map<String, Object>) results.get(i);
			String reviewerName = (String) itemMap.get("reviewer_name");
			int rating = (int) itemMap.get("rating");
			String content = (String) itemMap.get("content");
			content = content.replace("<", "&lt;");
			content = content.replace(">", "&gt;");			
			Date date = (Date) itemMap.get("reviewDate");
			String dateString = date.toString();
			brb.add(new BookReviewBean(bid, reviewerName, rating, content, dateString));
		}

		return brb;
	}
	
	public void publishReview(String bid, String reviewerName, int rating, String content, String dateString) throws SQLException {
		Date publishedDate = Date.valueOf(dateString);
		
			
		
		String query = "insert into BookReview(bid, reviewer_name, rating, content, reviewDate) values(?, ?, ?, ?, ?)";
		jdbcTemplate.update(query, bid, reviewerName, rating, content, publishedDate);
		
	}

}
