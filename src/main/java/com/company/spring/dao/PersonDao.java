package com.company.spring.dao;

import com.company.spring.dao.exception.DaoException;
import com.company.spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private static final String URL = "jdbc:postgresql://localhost:5632/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException cause) {
            cause.printStackTrace();
        }
    }

    public List<Person> getAll() {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var preparedStatement = connection.prepareStatement("SELECT id, name, surname, age, email FROM people.person")) {
            var resultSet = preparedStatement.executeQuery();
            List<Person> people = new ArrayList<>();
            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getInt("age")));
            }
            return people;
        } catch (SQLException cause) {
            throw new DaoException("No person found", cause);
        }
    }

    public Person getById(Integer id) {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var preparedStatement = connection.prepareStatement("SELECT id, name, surname, age, email FROM people.person WHERE id=?")) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Person person = null;
            if (resultSet.next()) {
                person = new Person(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"));
            }
            if (person == null) {
                throw new DaoException("Course with id: " + id + " is not found");
            }
            return person;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        }
    }

    public Person createPerson(Person person) {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var preparedStatement = connection.prepareStatement("INSERT INTO people.person values (?,?,?,?,?)")) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setString(5, person.getEmail());
            preparedStatement.executeUpdate();
            return person;
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        }
    }

    public Person updatePerson(Person person) {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var preparedStatement = connection.prepareStatement("UPDATE people.person SET id=?, name=?, surname=?, email=?, age=? WHERE id=?")) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setInt(5, person.getAge());
            preparedStatement.setInt(6, person.getId());
            var recordCount = preparedStatement.executeUpdate();
            if (recordCount != 0) {
                return person;
            }
            throw new DaoException("Person with id: " + person.getId() + " is not found");
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        }
    }

    public void deletePerson(Integer peopleId) {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var preparedStatement = connection.prepareStatement("DELETE FROM people.person WHERE id=?")) {
            preparedStatement.setInt(1, peopleId);
            preparedStatement.executeUpdate();
        } catch (SQLException cause) {
            throw new DaoException("Error when accessing the database ", cause);
        }
    }
}

