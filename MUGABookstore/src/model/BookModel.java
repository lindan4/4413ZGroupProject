package model;

import dao.BookDAO;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import bean.BookBean;

@Component
public class BookModel {
	
	@Autowired
	private BookDAO bDao;
	
	public BookModel() {
	}


	public LinkedList<BookBean> retrieveBookQuery(String query) throws Exception {
		return bDao.retrieveBookQuery(query);
	}

}
