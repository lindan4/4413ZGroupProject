package test;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.containsString;




import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import bean.UserBean;
import dao.UserDAO;
import model.UserModel;


@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/main-servlet.xml")
public class LoginTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserModel uModel;

	 
	@Before
	public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	 }


	
	@Test
	public void testLogin() throws SQLException, Exception{
		String email = "firstcust@example.com";
		String password = "1=1";
		
		mockMvc.perform(post("/login").param("email", email).param("password", password))
		.andExpect(model().attribute("loginError", containsString("The email or password is incorrect! Please try again.")));
		
//		UserBean ub = um.getUserByEmail(email, password);
//		
//		assertNull(ub);
	}
	
	@Test
	//Attempt to fetch one user using SQL injection method on email
	public void fetchUser() throws Exception {
		String email = " '' OR 1 = 1 LIMIT 1 -- ' ";
		String password = "12345678";
		
		
		UserBean ub = uModel.getUserByEmail(email, password);
		
		assertNull(ub);
		
	}
	
	

}
