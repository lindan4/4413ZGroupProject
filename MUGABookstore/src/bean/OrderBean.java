 package bean;

import java.util.Date;

public class OrderBean {

	private int id;
	private String status;
	private String bid;
	private int address;
	private String fname;
	private String lname;
	private String price;
	private String email;
	private Date date;

	public OrderBean() {

	}

	public OrderBean(int id, String fname, String lname, String status, int address, Date date, String email) {
		super();
		this.setId(id);
		this.setStatus(status);
		this.setAddress(address);
		this.setFname(fname);
		this.setLname(lname);
		this.setEmail(email);
		this.setDate(date);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
