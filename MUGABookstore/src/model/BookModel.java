package model;

import dao.BookDAO;
import dao.BookReviewDAO;

import java.sql.SQLException;
import java.util.LinkedList;

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
	

	public LinkedList<BookBean> retrieveBookQuery(String query) throws Exception {
		return bDao.retrieveBookQuery(query);
	}
	
	public BookBean getBookByID(String bid) throws Exception {
		return bDao.getBookByID(bid);
	}

	
	public LinkedList<BookReviewBean> retrieveBookReviews(String bid) throws Exception {
		return brDao.retrieveBookReviews(bid);
	}
}
