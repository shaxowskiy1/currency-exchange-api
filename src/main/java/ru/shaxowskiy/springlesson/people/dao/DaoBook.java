package ru.shaxowskiy.springlesson.people.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shaxowskiy.springlesson.people.model.Book;
import ru.shaxowskiy.springlesson.people.model.Person;

import java.util.List;

@Component
public class DaoBook {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoBook(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY id", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(Integer id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id =? ", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void save(Book book) {
         jdbcTemplate.update("INSERT INTO books (title, author, publication_year, isbn) VALUES (?, ?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getPublication_year(), book.getIsbn());
    }

    public void update(Integer id, Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, author = ?, publication_year = ?, isbn = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getPublication_year(), book.getIsbn(), book.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    public Book getBookByPersonId(Integer id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findFirst()
                .orElse(null);
    }

    // с помощью метода получаем владельца книги для передачи в представление
    public Person getOwnerByPersonId(Integer id) {
        return jdbcTemplate.query("SELECT * FROM person RIGHT JOIN books ON person.id = books.person_id WHERE books.id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findFirst()
                .orElse(null);
    }

}
