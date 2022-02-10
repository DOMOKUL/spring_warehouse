package com.company.spring.controllers;

import com.company.spring.dao.PersonDao;
import com.company.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getAllPeople(Model model){
        model.addAttribute("people",personDao.getAll());
        return "people/all-people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("person",personDao.getById(id));
        return "people/one-person";
    }

    @GetMapping("/new")
    public String createGetPerson(@ModelAttribute("person") Person person) {
        return "people/new-person";
    }

    @PostMapping()
    public String createPostPerson(@ModelAttribute("person") Person person){
        personDao.createPerson(person);
        return "redirect:/people";
    }
}
