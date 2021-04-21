package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dto.PersonInfoDTO;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.AlertUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Data
public class PersonInfoService {

    private JSONService jsonService;

    public PersonInfoService(JSONService jsonService){
        this.jsonService = jsonService;
    }
    public Iterable<PersonInfoDTO> getPersonInfo(String firstName, String lastName) {
        List<PersonInfoDTO> personInfos = new ArrayList<>();
        List<Person> persons = jsonService.getPersonsByFirstNameAndLastName(firstName, lastName);
        List<MedicalRecord> medicalRecords = jsonService.getAllMedicalRecordsByFirstNameAndLastName(firstName, lastName);
        if ((persons != null) && (medicalRecords != null)){
            for (int i = 0; i < persons.size(); i++){
                Person person = persons.get(i);
                MedicalRecord medicalRecord = medicalRecords.get(i);
                personInfos.add(new PersonInfoDTO(person.getFirstName(), person.getLastName(), person.getAddress(),
                        AlertUtils.calculateAge(medicalRecord.getBirthdate()), person.getEmail(), medicalRecord.getMedications(),
                        medicalRecord.getAllergies()));
            }
        }
        if (!personInfos.isEmpty()){
            log.info("person info successfully retrieved.");
            return personInfos;
        }
        log.error("Failed to retrieve person info");
        return null;
    }
}
