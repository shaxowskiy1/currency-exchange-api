package ru.shaxowskiy.springlesson.people.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.shaxowskiy.springlesson.people.model.Book;
import ru.shaxowskiy.springlesson.people.model.Person;
import java.util.List;

@Component
public class daoPeople {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public daoPeople(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person getPersonById(String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findFirst().orElse(null);
    }

    public List<Person> getListOfPeople() {

        return jdbcTemplate.query("select * from person ORDER BY id", new BeanPropertyRowMapper<>(Person.class));



    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, age, email, address) VALUES  (?, ?, ?, ?)", person.getName(),
                person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=?, address=? WHERE id=?", person.getName(),
                person.getAge(), person.getEmail(), person.getAddress(), person.getId());


    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

}


