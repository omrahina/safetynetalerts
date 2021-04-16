package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.JsonData;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetFirestationByStationNumber_success(){
        int stationNumber = 2;
        List<FireStation> fireStationList = jsonService.getAllFirestationsByStationNumber(stationNumber);

        assertThat(fireStationList).hasSize(3);
    }

    @Test
    public void testGetFirestationByStationNumber_fail(){
        int stationNumber = 6;
        List<FireStation> fireStationList = jsonService.getAllFirestationsByStationNumber(stationNumber);

        assertThat(fireStationList).isEmpty();
    }

    @Test
    public void testGetAllPersonsByAddress_success(){
        List<String> addresses = Arrays.asList("892 Downing Ct", "29 15th St", "951 LoneTree Rd");
        List<Person> personList = jsonService.getAllPersonsByAddress(addresses);

        assertThat(personList).hasSize(5).extracting(Person::getFirstName)
                .containsExactly("Jonanathan", "Sophia", "Warren", "Zach", "Eric");
    }

    @Test
    public void testGetAllPersonsByAddress_fail(){
        List<String> addresses = Arrays.asList("123 Downing Ct", "12 wall street");
        List<Person> personList = jsonService.getAllPersonsByAddress(addresses);

        assertNull(personList);
    }

    @Test
    public void testGetMedicalRecordsByFirstNameAndLastName_success(){
        String firstName = "Sophia";
        String lastName = "Zemicks";
        MedicalRecord medicalRecord = jsonService.getMedicalRecordByFirstNameAndLastName(firstName, lastName);

        assertEquals("03/06/1988", medicalRecord.getBirthdate());
    }

    @Test
    public void testGetMedicalRecordsByFirstNameAndLastName_fail(){
        String firstName = "Soleil";
        String lastName = "Zemicks";
        MedicalRecord medicalRecord = jsonService.getMedicalRecordByFirstNameAndLastName(firstName, lastName);

        assertNull(medicalRecord);
    }
}
