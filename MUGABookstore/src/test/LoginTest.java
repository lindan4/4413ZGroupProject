package test;

import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bean.UserBean;
import model.UserModel;

@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/main-servlet.xml")
public class LoginTest {
	
	@Autowired
	public UserModel um;
	
	@Test
	public void testLogin() throws SQLException, Exception{
		String email = "firstcust@example.com";
		String password = "1=1";
		
		UserBean ub = um.getUserByEmail(email, password);
		
		assertNull(ub);
		
	}

}
