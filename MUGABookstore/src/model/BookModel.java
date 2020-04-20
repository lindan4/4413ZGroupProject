package model;

import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.BookBean;
import bean.BookReviewBean;
import dao.BookDAO;
import dao.BookReviewDAO;

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
	
	//Retreve book instance, based on bid, from database
	public BookBean getBookByID(String bid) {
		return bDao.getBookByID(bid);
	}

	//Retrieve books sold during month from database
	public List<BookBean> listBooksSoldDuringMonth(Year year, Month month) {
		return bDao.listBooksSoldDuringMonth(year, month);
	}

	//Get all book reviews associated with a book using bid
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
