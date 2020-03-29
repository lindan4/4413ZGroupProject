package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bean.BookBean;
import bean.UserBean;
import mappers.BookRowMapper;
import mappers.UserRowMapper;

@Component
public class UserDAO {

	@Autowired
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private final UserRowMapper userRowMapper;

	public UserDAO(final JdbcTemplate jdbcTemplate, final UserRowMapper userRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.userRowMapper = userRowMapper;
	}

	public List<UserBean> listUsers() throws Exception {
		final String query = "SELECT * FROM Users";
		return jdbcTemplate.query(query, userRowMapper);
	}

	public void registerUser(String firstname, String lastname, String email, String password, String type) {
		String query = "insert into Users(firstname, lastname, email, password, user_type) values(?, ?, ?, ?, ?)";	
		jdbcTemplate.update(query, firstname, lastname, email, password, "Customer");

	}

	public UserBean getUserByEmail(String email) throws Exception, SQLException {
		String query = "select * from Users where email=?";

		List<?> results = jdbcTemplate.queryForList(query, email);
		Map<String, Object> itemMap = (Map<String, Object>) results.get(0);

		String firstname = (String) itemMap.get("firstname");
		String lastname = (String) itemMap.get("lastname");
		String password = (String) itemMap.get("password");
		String type = (String) itemMap.get("user_type");

		return results.size() > 0 ? new UserBean(firstname, lastname, email, password, type) : null;

	}
	
	

		
		
		
	}


