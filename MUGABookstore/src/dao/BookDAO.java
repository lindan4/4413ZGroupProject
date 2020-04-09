package dao;

import bean.AuthorBean;
import bean.BookAuthorBean;
import bean.BookBean;
import mappers.AuthorRowMapper;
import mappers.BookAuthorRowMapper;
import mappers.BookRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    private final BookRowMapper bookRowMapper;

    private final AuthorRowMapper authorRowMapper;

    private final BookAuthorRowMapper bookAuthorRowMapper;

    public BookDAO(final JdbcTemplate jdbcTemplate, final BookRowMapper bookRowMapper, final AuthorRowMapper authorRowMapper, final BookAuthorRowMapper bookAuthorRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
        this.authorRowMapper = authorRowMapper;
        this.bookAuthorRowMapper = bookAuthorRowMapper;
    }

    public List<BookBean> listBooks(int limit) throws Exception {
        final List<BookBean> books = jdbcTemplate.query("SELECT * FROM Book", bookRowMapper);
		final Map<String, BookBean> booksMap = books.stream().collect(Collectors.toMap(BookBean::getBid, bookBean -> bookBean));
        final List<AuthorBean> authors = jdbcTemplate.query("SELECT * FROM Author", authorRowMapper);
        final Map<String, AuthorBean> authorsMap = authors.stream().collect(Collectors.toMap(AuthorBean::getAuthorId, authorBean -> authorBean));
        final List<BookAuthorBean> bookAuthors = jdbcTemplate.query("SELECT * FROM BookAuthor", bookAuthorRowMapper);
		for (BookAuthorBean bookAuthor : bookAuthors) {
			final BookBean book = booksMap.get(bookAuthor.getBid());
			if (book != null) {
                final AuthorBean author = authorsMap.get(bookAuthor.getAuthorId());
                book.getAuthors().add(author.getAuthorName());
            }
		}
		List<?> categories = jdbcTemplate.queryForList("select b.bid, category from Book b, BookCategory bc, Category c where b.bid = bc.bid and bc.category_id = c.category_id");
		
		for (int j = 0; j < categories.size(); j++) {
			Map<String, Object> itemMap = (Map<String, Object>) categories.get(j);
			String bid = (String) itemMap.get("bid");
			String category = (String) itemMap.get("category");
			BookBean singleBook =  booksMap.get(bid);
			singleBook.getCategories().add(category);
		}

		return books.subList(0, limit);
/*        for (BookBean book : books) {
			final List<AuthorBean> bookAuthors = jdbcTemplate.query(
					"SELECT author.* FROM Book b JOIN Author author JOIN BookAuthor book_author WHERE book_author.author_id = author.author_id && b.bid = ? && b.bid = book_author.bid",
					authorRowMapper,
					book.getBid());
			book.setAuthors(bookAuthors);
		}*/


    }

    public TreeMap<String, BookBean> retrieveBookQuery(String queryInput) throws Exception, SQLException {

        TreeMap<String, BookBean> resultBean = new TreeMap<String, BookBean>();

        String query = "select * from Book b, BookCategory bc, Category c where b.bid = bc.bid and bc.category_id = c.category_id and b.title like ?";
        List<?> results = jdbcTemplate.queryForList(query, "%" + queryInput + "%");
        for (int i = 0; i < results.size(); i++) {
            Map<String, Object> itemMap = (Map<String, Object>) results.get(i);
            String bid = (String) itemMap.get("bid");
            String title = (String) itemMap.get("title");
            double price = (double) itemMap.get("price");
        	String category = (String) itemMap.get("category");

            if (resultBean.containsKey(bid)) {
            	resultBean.get(bid).getCategories().add(category);
            }
            else {
            	BookBean b = new BookBean(bid, title, price);
            	b.getCategories().add(category);
                resultBean.put(bid, b);
            }
        }

        return resultBean;
    }

    public BookBean getBookByID(String bid) throws Exception, SQLException {
        final List<BookBean> books = jdbcTemplate.query("SELECT * FROM Book where bid=?", bookRowMapper, bid);


        if (books.size() == 0) {
            return null;
        }

        final BookBean book = books.get(0);

        final List<AuthorBean> authors = jdbcTemplate.query(
                "SELECT author.* FROM Author as author JOIN BookAuthor as bookAuthor WHERE author.author_id = bookAuthor.author_id && bookAuthor.bid = ?",
                authorRowMapper, book.getBid());
        for (final AuthorBean author : authors) {
            book.getAuthors().add(author.getAuthorName());
        }

        return books.get(0);

    }

}
