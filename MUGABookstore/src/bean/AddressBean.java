package bean;

public class AddressBean {
	private String street;
	private String city;
	private String pvOrSt;
	private String country;
	private String postOrZip;
	private String phoneNo;
	
	public AddressBean(String street, String city, String pvOrSt, String country, String postOrZip, String phoneNo) {
		this.setStreet(street);
		this.setCity(city);
		this.setPvOrSt(pvOrSt);
		this.setCountry(country);
		this.setPostOrZip(postOrZip);
		this.setPhoneNo(phoneNo);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPvOrSt() {
		return pvOrSt;
	}

	public void setPvOrSt(String pvOrSt) {
		this.pvOrSt = pvOrSt;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostOrZip() {
		return postOrZip;
	}

	public void setPostOrZip(String postOrZip) {
		this.postOrZip = postOrZip;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	
	
	

}