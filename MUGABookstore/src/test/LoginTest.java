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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/main-servlet.xml")
public class LoginTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	 
	@Before
	public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	 }


	
	@Test
	public void testLogin() throws SQLException, Exception{
		String email = "firstcust@example.com";
		String password = "12345678";
		
		mockMvc.perform(post("/login").param("email", email).param("password", password))
		.andExpect(model().attribute("loginError", containsString("The email or password is incorrect! Please try again.")));
		
//		UserBean ub = um.getUserByEmail(email, password);
//		
//		assertNull(ub);
	}

}
