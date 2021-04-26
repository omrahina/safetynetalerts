package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dto.ResidentDTO;
import com.safetynet.safetynetalerts.dto.StationsDTO;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.AlertUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
public class StationsService {

    private JSONService jsonService;

    public StationsService(JSONService jsonService){
        this.jsonService = jsonService;
    }

    /**
     * Get all persons covered by a provided list of stations
     * @param stations A list of station numbers
     * @return Iterable<StationsDTO>
     */
    public Iterable<StationsDTO> getStationsCovered(List<Integer> stations) {
        List<StationsDTO> families = new ArrayList<>();
        List<FireStation> fireStations = jsonService.getAllFirestationsByStationNumber(stations);
        if (!fireStations.isEmpty()){
            List<String> addresses = fireStations.stream().map(FireStation::getAddress).collect(Collectors.toList());
            for (String address : addresses){
                List<Person> persons = jsonService.getAllPersonsByAddress(address);
                if(persons != null){
                    List<ResidentDTO> residents = persons.stream()
                            .map(person -> {
                                MedicalRecord medicalRecord = jsonService.getMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                                return new ResidentDTO(person.getFirstName(), person.getLastName(), person.getPhone(),
                                        AlertUtils.calculateAge(medicalRecord.getBirthdate()), medicalRecord.getMedications(), medicalRecord.getAllergies());
                            })
                            .collect(Collectors.toList());

                    families.add(new StationsDTO(address,residents));
                }
            }
        }
        if (!families.isEmpty()){
            log.info("Information on families covered by the stations retrieved.");
            return families;
        }
        log.error("Failed to retrieve information on families covered by the stations");
        return null;
    }
}
