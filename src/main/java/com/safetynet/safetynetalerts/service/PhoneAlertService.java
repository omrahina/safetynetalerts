package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
public class PhoneAlertService {

    private JSONService jsonService;

    public PhoneAlertService(JSONService jsonService){
        this.jsonService = jsonService;
    }

    /**
     * Get phone numbers of all persons covered by a fire station
     * @param firestation A known station number
     * @return Iterable<String> or null
     */
    public Iterable<String> getPhones(int firestation) {

        log.debug("searching for addresses corresponding to firestation " + firestation);
        List<FireStation> fireStations = jsonService.getAllFirestationsByStationNumber(firestation);
        if (!fireStations.isEmpty()){
            List<String> addresses = fireStations.stream().map(FireStation::getAddress).collect(Collectors.toList());
            List<Person> personsByAddress = jsonService.getAllPersonsByAddress(addresses);
            if (personsByAddress != null){
                log.debug("Retrieving phone numbers of people covered by firestation " + firestation);
                List<String> phones = personsByAddress.stream().map(Person::getPhone).collect(Collectors.toList());
                log.info("Phone number(s) successfully retrieved!");
                return phones;
            }
            log.error("No one found");
            return null;
        }
        log.error("No address corresponding to firestation " + firestation);
        return null;
    }
}
