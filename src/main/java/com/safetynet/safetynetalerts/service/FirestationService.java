package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dto.FireStationDTO;
import com.safetynet.safetynetalerts.dto.PersonDTO;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.utils.AlertUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class FirestationService {

    private JSONService jsonService;
    private List<FireStation> fireStationList;

    public FirestationService(JSONService jsonService){
        this.jsonService = jsonService;
        fireStationList = jsonService.getDataFromJSONFile().getFirestations();
    }

    public Iterable<FireStation> list() {
        return fireStationList;
    }

    /**
     * Add a mapping station/address
     * @param fireStation A valid FireStation object
     * @return The added mapping or null if the address is null
     */
    public FireStation addFirestation(FireStation fireStation) {
        if(fireStation.getAddress() != null){
            fireStationList.add(fireStation);
            log.info(fireStation.getAddress() + " " + fireStation.getStation() + " successfully added!");
            return fireStation;
        }
        log.error("Failed to add the firestation");

        return null;
    }

    /**
     * Update a mapping station/address
     * @param fireStation A mapping with an existing address
     * @return The updated mapping or null in case of a failure
     */
    public FireStation updateFirestation(FireStation fireStation) {

        Optional<FireStation> updatedFirestation = fireStationList.stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()))
                .peek(f -> f.setStation(fireStation.getStation()))
                .findFirst();
        if (updatedFirestation.isPresent()){
            log.info(fireStation.getAddress() + "'s station number successfully updated!");
            return updatedFirestation.get();
        }
        log.error("Failed to update " + fireStation.getAddress() + "'s station number ");

        return null;
    }

    /**
     * Delete a mapping
     * @param address The address of the mapping to be removed
     */
    public void deleteFirestation(String address){
        boolean removed = fireStationList.removeIf(f -> f.getAddress().equals(address));
        if (removed){
            log.info("Firestation successfully removed!");
        }else {
            log.error("Failed to delete the firestation");
        }
    }

    /**
     * Get the persons covered by a given fire station
     * @param stationNumber The fire station's number
     * @return Information on those persons in case the number exists, null otherwise
     */
    public FireStationDTO getPersonsCoveredByFirestation(int stationNumber) {

        log.debug("searching for addresses corresponding to stationNumber " + stationNumber);
        List<FireStation> fireStations = jsonService.getAllFirestationsByStationNumber(stationNumber);
        if (!fireStations.isEmpty()){
            List<String> addresses = fireStations.stream().map(FireStation::getAddress).collect(Collectors.toList());

            log.debug("searching for people covered by stationNumber " + stationNumber);
            List<PersonDTO> personsByAddress = jsonService.getAllPersonsByAddress(addresses).stream()
                    .map(person -> new PersonDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone()))
                    .collect(Collectors.toList());

            log.debug("Retrieving medical records");
            List<MedicalRecord> medicalRecords = new ArrayList<>();
            for (PersonDTO personDTO : personsByAddress){
                medicalRecords.add(jsonService.getMedicalRecordByFirstNameAndLastName(personDTO.getFirstName(), personDTO.getLastName()));
            }

            log.debug("Getting information about age");
            int adultNumber = (int) medicalRecords.stream().filter(mr -> AlertUtils.calculateAge(mr.getBirthdate()) > 18).count();
            int childrenNumber = (int) medicalRecords.stream().filter(mr -> AlertUtils.calculateAge(mr.getBirthdate()) <= 18).count();

            log.info("Person covered by stationNumber " + stationNumber + " successfully retrieved");
            return new FireStationDTO(personsByAddress, adultNumber, childrenNumber);
        }
        log.error("No address corresponding to stationNumber " + stationNumber);

        return null;
    }

}
