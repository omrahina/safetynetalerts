package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Person>> list(){
        log.info("Get person list call");
        List<Person> persons = personService.list();
        log.info("Get person list return => "+persons);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        log.info("addPerson call "+person);
        Person addedPerson = personService.addPerson(person);
        if (addedPerson != null){
            log.info("addPerson return => "+ addedPerson);
            return new ResponseEntity<>(addedPerson, HttpStatus.CREATED);
        }
        log.error("addPerson return => "+ null);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        log.info("Request updatePerson "+person);
        Person updatedPerson = personService.updatePerson(person);
        if(updatedPerson != null){
            log.info("Response updatePerson => "+updatedPerson);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        }
        log.error("Response updatePerson => "+null);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName){
        log.info("Request deletePerson "+ firstName + " " +lastName);
        boolean removed = personService.deletePerson(firstName, lastName);
        if(removed){
            log.info("Response deletePerson => "+true);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        log.error("Response deletePerson => "+false);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
