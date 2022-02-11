package com.company.spring.dao;

import com.company.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM people.person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(Integer id) {
        return jdbcTemplate.query("SELECT  * FROM people.person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO people.person values (DEFAULT,?,?,?,?)", person.getName(),person.getSurname(), person.getAge(), person.getEmail());
        return person;
    }

    public Person updatePerson(Person person) {
        jdbcTemplate.update("UPDATE people.person SET name=?, surname=?, age=?, email=? WHERE id=?",
                person.getName(),person.getSurname(),person.getAge(),person.getEmail(), person.getId());
        return person;
    }

    public void deletePerson(Integer peopleId) {
        jdbcTemplate.update("DELETE FROM people.person WHERE id=?", peopleId);
    }
}

