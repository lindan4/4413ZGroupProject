package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.lang.Nullable;

public class BookBean implements Comparable<BookBean> {
	
	private String bid;
	private String title;
	private String description;
	private String edition;
	private String format;
	private BigDecimal isbn;
	private int pages;
	private double rating;

	@Nullable
	private Integer count;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BookBean bookBean = (BookBean) o;
		return pages == bookBean.pages &&
				Double.compare(bookBean.rating, rating) == 0 &&
				rating_count == bookBean.rating_count &&
				Double.compare(bookBean.price, price) == 0 &&
				Objects.equals(bid, bookBean.bid) &&
				Objects.equals(title, bookBean.title) &&
				Objects.equals(description, bookBean.description) &&
				Objects.equals(edition, bookBean.edition) &&
				Objects.equals(format, bookBean.format) &&
				Objects.equals(isbn, bookBean.isbn) &&
				Objects.equals(imageUrl, bookBean.imageUrl) &&
				Objects.equals(authors, bookBean.authors) &&
				Objects.equals(categories, bookBean.categories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bid, title, description, edition, format, isbn, pages, rating, rating_count, imageUrl, price, authors, categories);
	}

	private int rating_count;
	private String imageUrl;
	private double price;

	private List<String> authors;
	private List<String> categories;

    public BookBean() {
    	this.authors = new ArrayList<>();
    }
	public BookBean(String bid, String title, double price) {
		this.setBid(bid);
		this.setTitle(title);
		this.setPrice(price);
		this.setAuthors(new ArrayList<>());
		this.setCategories(new ArrayList<>());
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

	@Override
	public int compareTo(BookBean arg0) {
		// TODO Auto-generated method stub
		return this.getBid().compareTo(arg0.getBid());
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public BigDecimal getIsbn() {
		return isbn;
	}

	public void setIsbn(BigDecimal isbn) {
		this.isbn = isbn;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getRating_count() {
		return rating_count;
	}

	public void setRating_count(int rating_count) {
		this.rating_count = rating_count;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	@Nullable
	public Integer getCount() {
		return count;
	}

	public void setCount(@Nullable Integer count) {
		this.count = count;
	}
}
