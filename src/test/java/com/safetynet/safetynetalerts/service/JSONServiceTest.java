package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.JsonData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JSONServiceTest {

    @Autowired
    private JSONService jsonService;


    @Test
    void testGetDataFromJSONFile_Ok(){
        JsonData jsonData = jsonService.getDataFromJSONFile();
        assertNotNull(jsonData);
        assertEquals("John",jsonData.getPersons().get(0).getFirstName());
    }

}
