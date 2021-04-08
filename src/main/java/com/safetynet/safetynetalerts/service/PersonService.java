package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class PersonService {

    private JSONService jsonService;

    private List<Person> personList;

    public PersonService(JSONService jsonService){
        this.jsonService = jsonService;
        personList = jsonService.getDataFromJSONFile().getPersons();
    }

    public Person addPerson(Person person){
        personList.add(person);
        return person;
    }

    public List<Person> list(){
        return personList;
    }

    public Person updatePerson(Person person) {

        Optional<Person> updatedPerson = personList.stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()) )
                .peek(p -> {
                    p.setAddress(person.getAddress());
                    p.setCity(person.getCity());
                    p.setZip(person.getZip());
                    p.setPhone(person.getPhone());
                    p.setEmail(person.getEmail());
                })
                .findFirst();
        if (updatedPerson.isPresent()){
            return updatedPerson.get();
        }else {
            log.info("Failed to update person "+person.getFirstName() + " " +person.getLastName());
        }

        return null;
    }

    public Person deletePerson(Person person) {

        return null;
    }
}
