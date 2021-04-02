package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PersonService {

    private JSONService jsonService;

    private List<Person> personList;

    public PersonService(JSONService jsonService){
        this.jsonService = jsonService;
        personList = jsonService.getDataFromJSONFile().getPersons();
    }

    public void addPerson(Person person){
        personList.add(person);
    }

    public List<Person> list(){
        return personList;
    }
}
