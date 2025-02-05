package model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import bean.UserBean;
import dao.UserDAO;

@Component
public class UserModel {

	@Autowired
	private UserDAO uDAO;

	// Return list of users etc.

	public List<UserBean> listUsers() throws Exception {
		return this.uDAO.listUsers();

	}

	//Register use to database
	public void registerUser(UserBean ub) throws SQLException, Exception {
		
		boolean t = uDAO.isUserRegistered(ub.getEmail());
		if(t == false) {
		uDAO.registerUser(ub.getFirstname(), ub.getLastname(), ub.getEmail(), ub.getPassword(), ub.getType());
		}
		else {
			//If user already exists, throw exception
			throw new DuplicateKeyException(ub.getEmail());
		}

	}

	//Retireve user information from database
	public UserBean getUserByEmail(String email, String password) throws SQLException, Exception {

		return uDAO.getUserByEmail(email, password);

	}
	


}
