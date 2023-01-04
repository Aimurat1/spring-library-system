package kz.project1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kz.project1.models.Book;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        // return jdbcTemplate.query("SELECT * FROM Book ORDER BY book_id ASC",
        // new BeanPropertyRowMapper<>(Book.class));

        return jdbcTemplate.query("SELECT * FROM Book ORDER BY book_id ASC",
                new BookMapper());

    }

    public Book getBook(int id) {
        // return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new
        // Object[] { id },
        // new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);

        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[] { id },
                new BookMapper()).stream().findAny().orElse(null);

    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void updateBook(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE book_id = ?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void updateReader(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", person_id, book_id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", new Object[] { id });
    }

    public void deleteReader(int id) {
        jdbcTemplate.update("UPDATE Book set person_id = NULL WHERE book_id = ?",
                id);
    }

}
