package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bean.UserBean;

@Component
public class UserRowMapper implements RowMapper<UserBean> {

	public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserBean user = new UserBean();
		//user.setUid(rs.getInt("uid"));
		user.setEmail(rs.getString("email"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setPassword(rs.getString("password"));
		user.setType(rs.getString("type"));
		return user;
	}
}