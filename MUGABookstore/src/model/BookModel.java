package model;

import dao.BookDAO;
import dao.BookReviewDAO;
import helper.HelperLib;

import java.sql.SQLException;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import bean.BookBean;
import bean.BookReviewBean;

@Component
public class BookModel {
	
	@Autowired
	private BookDAO bDao;
	
	@Autowired
	private BookReviewDAO brDao;

	public List<BookBean> listBooks(int limit) {
		return this.bDao.listBooks(limit, 0);
	}

	public List<BookBean> listBooks(Integer limit, Integer offset) {
		return this.bDao.listBooks(limit, offset);
	}

	public List<BookBean> listBooks(Integer limit, Integer offset, String categoryFilter) {
		return this.bDao.listBooks(limit, offset, categoryFilter);
	}

	public List<BookBean> listBooks(Integer limit, String categoryFilter) {
		return this.bDao.listBooks(limit, 0, categoryFilter);
	}

	public TreeMap<String,BookBean> retrieveBookQuery(String query) throws Exception {
		return bDao.retrieveBookQuery(query);
	}
	
	public BookBean getBookByID(String bid) {
		return bDao.getBookByID(bid);
	}

	public List<BookBean> listBooksSoldDuringMonth(Year year, Month month) {
		return bDao.listBooksSoldDuringMonth(year, month);
	}

	
	public LinkedList<BookReviewBean> retrieveBookReviews(String bid) throws Exception {
		return brDao.retrieveBookReviews(bid);
	}

	public List<BookBean> top10BooksSoldLifetime() {
		return bDao.top10BooksSoldLifetime();
	}
	
	public void publishReview(String bid, String reviewerName, int rating, String content, String dateString) throws Exception {
		
		brDao.publishReview(bid, reviewerName, rating, content, dateString);
	}
}
