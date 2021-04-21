package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
public class CommunityEmailService {

    private JSONService jsonService;

    public CommunityEmailService(JSONService jsonService){
        this.jsonService = jsonService;
    }
    public Iterable<String> getEmailsByCity(String city) {
        List<Person> persons = jsonService.getPersonsByCity(city);
        if(persons != null){
            List<String> emails = persons.stream().map(Person::getEmail).collect(Collectors.toList());
            log.info("Emails retrieved");
            return emails;
        }
        log.error("Failed to retrieve the emails");
        return null;
    }
}
