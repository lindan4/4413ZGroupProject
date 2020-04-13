package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bean.OrderBean;

@Component
public class OrderRowMapper implements RowMapper<OrderBean> {

	public OrderBean mapRow(ResultSet rs, int rowNum) throws SQLException {

		OrderBean order = new OrderBean();

		order.setId(rs.getInt("id"));
		order.setLname(rs.getString("lname"));
		order.setFname(rs.getString("fname"));
		order.setStatus(rs.getString("status"));
		order.setAddress(rs.getInt("address"));

		return order;

	}

}
