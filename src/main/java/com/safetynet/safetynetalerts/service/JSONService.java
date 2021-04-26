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

    /**
     * Retrieve data from the JSON file
     * @return a JAVA object
     */
    public JsonData getDataFromJSONFile(){

        ObjectMapper mapper = new ObjectMapper();

        try {
            log.info("JSON data successfully retrieved");
            return mapper.readValue(jsonDataFile.getFile(), JsonData.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    /**
     * Get all mappings station/address corresponding to a station number
     * @param stationNumber an int
     * @return A list of mappings
     */
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

    /**
     * Get all mappings station/address corresponding to a list of station numbers
     * @param stations A List of int
     * @return A list of mappings
     */
    public List<FireStation> getAllFirestationsByStationNumber(List<Integer> stations){
        List<FireStation> fireStations = getDataFromJSONFile().getFirestations().stream().filter(f -> stations.contains(f.getStation()))
                .collect(Collectors.toList());
        if (!fireStations.isEmpty()){
            log.info("Matching fire station(s) found");
            return fireStations;
        }
        log.error("No matching fire station found!");
        return List.of();
    }

    /**
     * Get the mapping station/address corresponding to a given address
     * @param address A String
     * @return A mapping or null when the address is unknown
     */
    public FireStation getFirestationByAddress(String address){
        Optional <FireStation> fireStation = getDataFromJSONFile().getFirestations().stream()
                .filter(f -> address.equals(f.getAddress()))
                .findFirst();
        if (fireStation.isPresent()){
            log.info("Matching fire station found");
            return fireStation.get();
        }
        log.error("No matching fire station");
        return null;
    }

    /**
     * Get all persons living at a given list of addresses
     * @param addresses A list of String
     * @return A list of persons or null
     */
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

    /**
     * Get all persons living at a specific address
     * @param address A String
     * @return A list of persons or null
     */
    public List<Person> getAllPersonsByAddress(String address){
        List<Person> persons = getDataFromJSONFile().getPersons().stream().filter(p -> address.equals(p.getAddress()))
                .collect(Collectors.toList());
        if (!persons.isEmpty()){
            log.info("Matching person(s) found");
            return persons;
        }
        log.error("No match found!");
        return null;
    }

    /**
     * Find all persons matching a first name and last name
     * @param firstName A String
     * @param lastName A String
     * @return A list of persons or null
     */
    public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName){
        List<Person> persons = getDataFromJSONFile().getPersons().stream()
                .filter(person -> firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName()))
                .collect(Collectors.toList());
        if (!persons.isEmpty()){
            log.info("Matching person(s) found");
            return persons;
        }
        log.error("No match found!");
        return null;
    }

    /**
     * Get all persons living in the given city
     * @param city A String
     * @return @return A list of persons or null
     */
    public List<Person> getPersonsByCity(String city){
        List<Person> persons = getDataFromJSONFile().getPersons().stream()
                .filter(person -> city.equals(person.getCity()))
                .collect(Collectors.toList());
        if (!persons.isEmpty()){
            log.info("Person(s) living in "+ city + " successfully retrieved");
            return persons;
        }
        log.error("No one found for "+ city);
        return null;
    }

    /**
     * Get a medical record based on a first and last name
     * @param firstName A String
     * @param lastName A String
     * @return A medical record if exists, null otherwise
     */
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

    /**
     * Get all medical records matching a first name and last name
     * @param firstName A String
     * @param lastName A String
     * @return A list of records or null when no match found
     */
    public List<MedicalRecord> getAllMedicalRecordsByFirstNameAndLastName(String firstName, String lastName){
        List<MedicalRecord> medicalRecords = getDataFromJSONFile().getMedicalrecords().stream()
                .filter(mr -> firstName.equals(mr.getFirstName()) && lastName.equals(mr.getLastName()))
                .collect(Collectors.toList());
        if(!medicalRecords.isEmpty()){
            log.info("Matching medical record(s) found");
            return medicalRecords;
        }
        log.error("Medical record not found!");
        return null;
    }

}
