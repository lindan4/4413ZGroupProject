package bean;

public class BookReviewBean {
	private String bid;
	private String reviewerName;
	private int rating;
	private String content;
	private String date;
	
	public BookReviewBean(String bid, String reviewerName, int rating, String content, String date) {
		this.setBid(bid);
		this.setReviewerName(reviewerName);
		this.setRating(rating);
		this.setContent(content);
		this.setDate(date);
	}
	
	public String getBid() {
		return bid;
	}
	
	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getReviewerName() {
		return reviewerName;
	}
	
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	

}
