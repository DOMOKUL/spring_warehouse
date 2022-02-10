package com.company.spring.dao;

import com.company.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class PersonDao {

    private List<Person> people;

    {
        people = List.of(new Person(1L, "Ivan", "Ivanov", "ivan@mail.com"),
                new Person(2L, "Petr", "Petrov", "petr@mail.ro"),
                new Person(3L, "Alex", "Alexov", "alex@mail.ro"),
                new Person(4L, "Masha", "Mashanina", "masha@mail.et"));
    }

    public List<Person> getAll() {
        return people;
    }

    public Person getById(Integer id) {
        return people.get(id - 1);
    }

    public void createPerson(Person person) {
        person.setId(10L);
        people.add(person);
    }
}

