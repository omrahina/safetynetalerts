package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class FirestationService {

    JSONService jsonService;
    private List<FireStation> fireStationList;

    public FirestationService(JSONService jsonService){
        this.jsonService = jsonService;
        fireStationList = jsonService.getDataFromJSONFile().getFirestations();
    }

    public Iterable<FireStation> list() {
        return fireStationList;
    }

    public FireStation addFirestation(FireStation fireStation) {
        //Shall we test the case station is not specified meaning O?
        if(fireStation.getAddress() != null){
            fireStationList.add(fireStation);
            log.info(fireStation.getAddress() + " " + fireStation.getStation() + " successfully added!");
            return fireStation;
        }
        log.error("Failed to add the firestation");

        return null;
    }

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

    public void deleteFirestation(String address){
        boolean removed = fireStationList.removeIf(f -> f.getAddress().equals(address));
        if (removed){
            log.info("Firestation successfully removed!");
        }else {
            log.error("Failed to delete the firestation");
        }
    }

}
