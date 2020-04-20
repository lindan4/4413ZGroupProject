package bean;

import java.util.Map;

public class UserBean {

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String type;

	public UserBean() {

	}

	public UserBean(String firstname, String lastname, String email, String password, String type) {
		// this.setUid(uid);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setEmail(email);
		this.setPassword(password);
		this.setType(type);
		
	
	}

	/*
	 * public int getUid() { return uid; }
	 * 
	 * public void setUid(int uid) { this.uid = uid; }
	 * 
	 */

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
