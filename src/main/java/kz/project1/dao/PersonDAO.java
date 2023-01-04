package kz.project1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kz.project1.models.Book;
import kz.project1.models.Person;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person ORDER BY person_id ASC",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new Object[] { id },
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Book> getPersonBooks(int id) {
        return jdbcTemplate.query(
                "SELECT book.book_id, book.name, book.author, book.year, book.person_id FROM Person JOIN Book ON person.person_id = book.person_id WHERE book.person_id = ?",
                new Object[] { id }, new BookMapper());
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, surname, year) VALUES(?, ?, ?)",
                person.getName(), person.getSurname(), person.getYear());
    }

    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name = ?, surname = ?, year = ? WHERE person_id = ?",
                updatedPerson.getName(), updatedPerson.getSurname(), updatedPerson.getYear(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", new Object[] { id });
    }

}
