package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.JsonData;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JSONService {

    @Value("classpath:data.json")
    Resource jsonDataFile;

    public JsonData getDataFromJSONFile(){

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonDataFile.getFile(), JsonData.class);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return null;
    }

    public List<FireStation> getAllFirestationsByStationNumber(int stationNumber){
        List<FireStation> fireStations = getDataFromJSONFile().getFirestations().stream().filter(f -> f.getStation() == stationNumber)
                .collect(Collectors.toList());
        if (!fireStations.isEmpty()){
            log.info("Matching fire station(s) found");
            return fireStations;
        }
        log.error("No matching fire station found!");
        return List.of();
    }

    public List<Person> getAllPersonsByAddress(List<String> addresses){
        List<Person> persons = getDataFromJSONFile().getPersons().stream().filter(p -> addresses.contains(p.getAddress()))
                .collect(Collectors.toList());
        if (!persons.isEmpty()){
            log.info("Matching person(s) found");
            return persons;
        }
        log.error("No match found!");
        return null;
    }

    public MedicalRecord getMedicalRecordByFirstNameAndLastName(String firstName, String lastName){
        Optional<MedicalRecord> medicalRecord = getDataFromJSONFile().getMedicalrecords().stream()
                .filter(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))
                .findAny();

        if (medicalRecord.isPresent()){
            log.info("Matching medical record found");
            return medicalRecord.get();
        }
        log.error("Medical record not found!");
        return null;
    }

}
