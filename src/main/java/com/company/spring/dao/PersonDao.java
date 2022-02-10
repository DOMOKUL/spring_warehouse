package com.company.spring.dao;

import com.company.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom","Ivanov","posa1@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob","Petrov", "posa2@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike","Sidorov", "posf@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", "Katuea", "21sae@mail.ru"));
    }

    public List<Person> getAll() {
        return people;
    }

    public Person getById(Integer id) {
        return people.stream().filter(person -> person.getId().equals(id)).findAny().orElse(null);
    }

    public void createPerson(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void updatePerson(Integer id, Person updatedPerson) {
        Person personToBeUpdated = getById(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setSurname(updatedPerson.getSurname());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void deletePerson(Integer id) {
        people.removeIf(person -> person.getId().equals(id));
    }
}

