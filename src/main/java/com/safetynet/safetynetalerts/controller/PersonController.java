package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
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

    @PutMapping("/person")
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @DeleteMapping
    public void deletePerson(@RequestParam String firstName, @RequestParam String lastName){
        boolean removed = personService.deletePerson(firstName, lastName);
    }
}
