package test;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;




import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import bean.UserBean;
import model.UserModel;


@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/main-servlet.xml")
public class AdminTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private UserModel um;
	
	private MockMvc mockMvc;

	 
	@Before
	public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	 }


	
	@Test
	public void testAdminAccess() throws SQLException, Exception{

		//Invalid user
		String emailCust = "firstcust@example.com";
		String passwordCust = "12345678";
		
		UserBean ubCust = um.getUserByEmail(emailCust, passwordCust);
		MockHttpSession mtsCust = new MockHttpSession();
		mtsCust.setAttribute("loggedInUser", ubCust);
		
//		mockMvc.perform(post("/login").param("email", email).param("password", password)).andDo(handler);
		
		mockMvc.perform(get("/admin").session(mtsCust)).andExpect(model().attribute("admin", is(false)));
		
		
		//Valid user
		String emailAdmin = "firstadmin@example.com";
		String passwordAdmin = "12345678";
		
		UserBean ubAdmin = um.getUserByEmail(emailAdmin, passwordAdmin);
		MockHttpSession mtsAdmin = new MockHttpSession();
		mtsAdmin.setAttribute("loggedInUser", ubAdmin);
		
//		mockMvc.perform(post("/login").param("email", email).param("password", password)).andDo(handler);
		
		mockMvc.perform(get("/admin").session(mtsAdmin)).andExpect(model().attribute("admin", is(true)));
		
		
	}

}
