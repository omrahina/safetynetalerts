package com.safetynet.safetynetalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void testAddPerson() throws Exception {

        Person person = new Person("Ran", "Tan", "wall street",
                "Cergy", "95000", "01010103", "tan@gmail.com");

        String requestBodyJson = new ObjectMapper().writeValueAsString(person);

        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person person = new Person("John", "Boyd", "12 street",
                "Paris", "75015", "01010101", "yan@gmail.com");
        String requestBodyJson = new ObjectMapper().writeValueAsString(person);

        mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {

        Person person = new Person("John", "Boyd", "12 street",
                "Paris", "75015", "01010101", "yan@gmail.com");
        String requestBodyJson = new ObjectMapper().writeValueAsString(person);

        mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isOk());
    }
}
