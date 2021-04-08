package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/person")
    public List<Person> list(){
        return personService.list();
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person){
       return personService.addPerson(person);
    }

}
