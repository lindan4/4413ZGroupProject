package dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bean.AddressBean;

@Component
public class AddressDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertAddress(AddressBean ab) throws SQLException {
		
		String street = ab.getStreet();
		String province = ab.getPvOrSt();
		String phone = ab.getPhoneNo();
		String city = ab.getCity();
		String country = ab.getCountry();
		String post = ab.getPostOrZip();
		//int id = ab.getId();
		int id = this.getAddressId();
		
		String query = "insert into Address(id, street, province, country, zip, phone) values(?,?,?,?,?,?)";
		jdbcTemplate.update(query, id, street, province, country, post, phone);
		
	}
	
	
	public int getAddressId() throws SQLException {

		String query = "select max(id) from Address";
		int aid = jdbcTemplate.queryForObject(query, Integer.class);
		return (aid + 1);
	}
	
	
	
	

}
