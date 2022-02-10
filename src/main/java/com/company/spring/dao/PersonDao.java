package com.company.spring.dao;

import com.company.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonDao {

    private List<Person> people;

    {
        people = List.of(new Person(1L,"Ivan"),
                new Person(2L,"Petr"),
                new Person(3L, "Alex"),
                new Person(4L, "Masha"));
    }

    public List<Person> getAll(){
        return people;
    }

    public Person getById(Integer id){
        return people.get(id-1);
    }
}
