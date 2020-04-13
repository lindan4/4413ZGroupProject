package bean;

public class OrderBean {

	private int id;
	private String status;
	private String bid;
	private String address;
	private String fname;
	private String lname;
	private String price;

	public OrderBean() {

	}

	public OrderBean(int id, String status, String fname, String lname, String address) {
		super();
		this.setId(id);
		this.setStatus(status);
		//this.setBid(bid);
		this.setAddress(address);
		this.setFname(fname);
		this.setLname(lname);
		//this.setPrice(price);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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

}
