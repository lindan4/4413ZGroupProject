package model;

import dao.BookDAO;

import java.sql.SQLException;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import bean.BookBean;

@Component
public class BookModel {
	
	private final BookDAO bDao;
	
	public BookModel(BookDAO bDao) {
		this.bDao = bDao;
	}


	public LinkedList<BookBean> retrieveBookQuery(String query) throws Exception {
		return bDao.retrieveBookQuery(query);
	}
	
	public BookBean getBookByID(String bid) throws Exception {
		return bDao.getBookByID(bid);
	}

}
