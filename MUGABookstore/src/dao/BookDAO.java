package dao;

import bean.*;
import mappers.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
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

    private final BookCategoryRowMapper bookCategoryRowMapper;

    private final CategoryRowMapper categoryRowMapper;

    public BookDAO(
            final JdbcTemplate jdbcTemplate,
            final BookRowMapper bookRowMapper,
            final AuthorRowMapper authorRowMapper,
            final BookAuthorRowMapper bookAuthorRowMapper,
            final BookCategoryRowMapper bookCategoryRowMapper,
            final CategoryRowMapper categoryRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
        this.authorRowMapper = authorRowMapper;
        this.bookAuthorRowMapper = bookAuthorRowMapper;
        this.bookCategoryRowMapper = bookCategoryRowMapper;
        this.categoryRowMapper = categoryRowMapper;
    }

    public List<BookBean> listBooks(Integer limit, Integer offset) {
        final List<BookBean> books = jdbcTemplate.query("SELECT * FROM Book LIMIT ? OFFSET ?", bookRowMapper, limit, offset);
        final Map<String, BookBean> booksMap = books.stream().collect(
                Collectors.toMap(BookBean::getBid, bookBean -> bookBean));

        final List<AuthorBean> authors = jdbcTemplate.query(
                "SELECT author.* " +
                "FROM Author as author " +
                "INNER JOIN (SELECT bookAuthor.author_id " +
                    "FROM BookAuthor as bookAuthor " +
                    "INNER JOIN (SELECT bid FROM Book LIMIT ? OFFSET ?) AS book " +
                    "ON bookAuthor.bid = book.bid) AS bookAuthors " +
                "ON bookAuthors.author_id = author.author_id " +
                "GROUP BY author_id"
            , authorRowMapper, limit, offset);
        final Map<String, AuthorBean> authorsMap = authors.stream().collect(
                Collectors.toMap(AuthorBean::getAuthorId, authorBean -> authorBean));

        final List<BookAuthorBean> bookAuthors = jdbcTemplate.query(
                "SELECT bookAuthors.* " +
                        "FROM Author as author " +
                        "INNER JOIN (SELECT bookAuthor.* " +
                            "FROM BookAuthor as bookAuthor " +
                            "INNER JOIN (SELECT bid FROM Book LIMIT ? OFFSET ?) AS book " +
                            "ON bookAuthor.bid = book.bid) AS bookAuthors " +
                        "ON bookAuthors.author_id = author.author_id " +
                        "GROUP BY id;",
            bookAuthorRowMapper, limit, offset);

        for (BookAuthorBean bookAuthor : bookAuthors) {
            final BookBean book = booksMap.get(bookAuthor.getBid());
            final AuthorBean author = authorsMap.get(bookAuthor.getAuthorId());
            book.getAuthors().add(author.getAuthorName());
        }

        final List<CategoryBean> categories = jdbcTemplate.query(
                "SELECT category.* " +
                "FROM Category as category " +
                "INNER JOIN (" +
                    "SELECT bookCategory.* FROM BookCategory as bookCategory " +
                    "INNER JOIN (" +
                        "SELECT bid FROM Book LIMIT ? OFFSET ?) as book " +
                    "ON bookCategory.bid = book.bid) as bookCategories " +
                "ON bookCategories.category_id = category.category_id " +
                "GROUP BY category_id",
            categoryRowMapper, limit, offset);

        final Map<String, CategoryBean> categoryMap = categories.stream().collect(
                Collectors.toMap(CategoryBean::getCategory_id, categoryBean -> categoryBean));

        final List<BookCategoryBean> bookCategories = jdbcTemplate.query(
                "SELECT bookCategories.* " +
                        "FROM Category as category " +
                        "INNER JOIN (SELECT bookCategory.* " +
                            "FROM BookCategory as bookCategory " +
                            "INNER JOIN (SELECT bid FROM Book LIMIT ? OFFSET ?) AS book " +
                            "ON bookCategory.bid = book.bid) as bookCategories " +
                        "ON bookCategories.category_id = category.category_id",
                bookCategoryRowMapper, limit, offset
        );

        for (BookCategoryBean bookCategory : bookCategories) {
            final BookBean book = booksMap.get(bookCategory.getBid());
            final CategoryBean category = categoryMap.get(bookCategory.getCategoryId());
            book.getCategories().add(category.getCategory());
        }

        return books;
    }

    public List<BookBean> listBooks(Integer limit, Integer offset, String categoryFilter) {
        String booksQuery = "SELECT book.* FROM Book as book " +
                "INNER JOIN (" +
                "SELECT bookCategory.* FROM BookCategory as bookCategory " +
                "INNER JOIN (SELECT * FROM Category AS category WHERE category.category = ?) AS categories " +
                "ON bookCategory.category_id = categories.category_id) as bookCategories " +
                "ON bookCategories.bid = book.bid " +
                "GROUP BY bid " +
                "LIMIT ? " +
                "OFFSET ? ";
        final List<BookBean> books = jdbcTemplate.query(booksQuery, bookRowMapper, categoryFilter, limit, offset);
        final Map<String, BookBean> booksMap = books.stream().collect(
                Collectors.toMap(BookBean::getBid, bookBean -> bookBean));

        final List<AuthorBean> authors = jdbcTemplate.query(
                "SELECT author.* " +
                        "FROM Author as author " +
                        "INNER JOIN (SELECT bookAuthor.author_id " +
                        "FROM BookAuthor as bookAuthor " +
                        "INNER JOIN (" + booksQuery + ") AS book " +
                        "ON bookAuthor.bid = book.bid) AS bookAuthors " +
                        "ON bookAuthors.author_id = author.author_id " +
                        "GROUP BY author_id"
                , authorRowMapper, categoryFilter, limit, offset);
        final Map<String, AuthorBean> authorsMap = authors.stream().collect(
                Collectors.toMap(AuthorBean::getAuthorId, authorBean -> authorBean));

        final List<BookAuthorBean> bookAuthors = jdbcTemplate.query(
                "SELECT bookAuthors.* " +
                        "FROM Author as author " +
                        "INNER JOIN (SELECT bookAuthor.* " +
                        "FROM BookAuthor as bookAuthor " +
                        "INNER JOIN (" + booksQuery + ") AS book " +
                        "ON bookAuthor.bid = book.bid) AS bookAuthors " +
                        "ON bookAuthors.author_id = author.author_id " +
                        "GROUP BY id;",
                bookAuthorRowMapper, categoryFilter, limit, offset);

        for (BookAuthorBean bookAuthor : bookAuthors) {
            final BookBean book = booksMap.get(bookAuthor.getBid());
            final AuthorBean author = authorsMap.get(bookAuthor.getAuthorId());
            book.getAuthors().add(author.getAuthorName());
        }

        final List<CategoryBean> categories = jdbcTemplate.query(
                "SELECT category.* " +
                        "FROM Category as category " +
                        "INNER JOIN (" +
                        "SELECT bookCategory.* FROM BookCategory as bookCategory " +
                        "INNER JOIN (" + booksQuery + ") as book " +
                        "ON bookCategory.bid = book.bid) as bookCategories " +
                        "ON bookCategories.category_id = category.category_id " +
                        "GROUP BY category_id",
                categoryRowMapper, categoryFilter, limit, offset);

        final Map<String, CategoryBean> categoryMap = categories.stream().collect(
                Collectors.toMap(CategoryBean::getCategory_id, categoryBean -> categoryBean));

        final List<BookCategoryBean> bookCategories = jdbcTemplate.query(
                "SELECT bookCategories.* " +
                        "FROM Category as category " +
                        "INNER JOIN (SELECT bookCategory.* " +
                        "FROM BookCategory as bookCategory " +
                        "INNER JOIN (" + booksQuery + ") AS book " +
                        "ON bookCategory.bid = book.bid) as bookCategories " +
                        "ON bookCategories.category_id = category.category_id",
                bookCategoryRowMapper, categoryFilter, limit, offset
        );

        for (BookCategoryBean bookCategory : bookCategories) {
            final BookBean book = booksMap.get(bookCategory.getBid());
            final CategoryBean category = categoryMap.get(bookCategory.getCategoryId());
            book.getCategories().add(category.getCategory());
        }

        return books;
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
            } else {
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
                "SELECT author.* FROM Author as author " +
                        "JOIN BookAuthor as bookAuthor " +
                        "WHERE author.author_id = bookAuthor.author_id && bookAuthor.bid = ?",
                authorRowMapper, book.getBid());

        for (final AuthorBean author : authors) {
            book.getAuthors().add(author.getAuthorName());
        }

        final List<CategoryBean> categories = jdbcTemplate.query(
                "SELECT category.* FROM Category as category " +
                        "JOIN BookCategory as bookCategory " +
                        "WHERE category.category_id = bookCategory.category_id && bookCategory.bid = ?",
                categoryRowMapper, book.getBid());

        for (final CategoryBean category : categories) {
            book.getCategories().add(category.getCategory());
        }

        return books.get(0);

    }

}
