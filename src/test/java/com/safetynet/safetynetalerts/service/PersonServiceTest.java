package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    private static List<Person> persons = new ArrayList<>();

    @BeforeAll
    private static void setUp(){
        Person person1 = new Person("Yan", "Fan", "12 street",
                "Paris", "75015", "01010101", "yan@gmail.com");
        Person person2 = new Person("Lan", "Han", "13 street",
                "Massy", "91300", "01010102", "lan@gmail.com");
        persons.add(person1);
        persons.add(person2);
    }

    @BeforeEach
    private void setUpPerTest(){
        personService.setPersonList(persons);
    }

    @Test
    public void testAddPerson(){
        Person person3 = new Person("Ran", "Tan", "wall street",
                "Cergy", "95000", "01010103", "tan@gmail.com");
        Person result = personService.addPerson(person3);

        assertEquals("tan@gmail.com", result.getEmail());
        assertEquals("95000", personService.getPersonList().get(2).getZip());
    }

    @Test
    public void testList(){
        List<Person> result = personService.list();

       assertEquals(2, result.size());
       assertEquals("Paris", result.get(0).getCity());
    }

    @Test
    public void testUpdatePerson_Ok(){
        Person person = new Person("Lan", "Han", "wall street",
                "Cergy", "95000", "01010103", "tan@gmail.com");

        Person result = personService.updatePerson(person);

        assertNotNull(person);
        assertEquals("wall street", result.getAddress());
    }
}
