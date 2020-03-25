package model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.UserBean;
import dao.UserDAO;

@Component
public class UserModel {
	
	@Autowired
	private UserDAO uDAO;
	
	//Return list of users etc.
	
	public List<UserBean> listUsers() throws Exception {
		return this.uDAO.listUsers();
	
	}
	
	public void registerUser(UserBean ub) {
		uDAO.registerUser(ub.getFirstname(), ub.getLastname(), ub.getEmail(), ub.getPassword(), ub.getType());
	}
	
	public UserBean getUserByEmail(String email) throws SQLException, Exception {
		return uDAO.getUserByEmail(email);
	}
	

}
