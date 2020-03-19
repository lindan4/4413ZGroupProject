package bean;

public class BookBean implements Comparable<BookBean> {
	
	private String bid;
	private String title;
	private double price;
	private String category;

    public BookBean() {
    }
	public BookBean(String bid, String title, double price, String category) {
		this.setBid(bid);
		this.setTitle(title);
		this.setPrice(price);
		this.setCategory(category);
	}
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	@Override
	public int compareTo(BookBean arg0) {
		// TODO Auto-generated method stub
		return this.getBid().compareTo(arg0.getBid());
	}
	
	
	
	
	

}
