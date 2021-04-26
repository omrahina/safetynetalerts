package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.FireStationDTO;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
@Slf4j
public class FirestationController {

   private FirestationService firestationService;

    public FirestationController(FirestationService firestationService){
        this.firestationService = firestationService;
    }

    @PostMapping
    public ResponseEntity<FireStation> addFirestation(@RequestBody FireStation fireStation){
        log.info("addFirestation request "+ fireStation);
        FireStation mapping = firestationService.addFirestation(fireStation);
        log.info("addFirestation response "+ mapping);
        if (mapping != null){
            return new ResponseEntity<>(mapping, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<FireStation> updatedFirestation(@RequestBody FireStation fireStation){
        log.info("updateFirestation request "+ fireStation);
        FireStation updatedFireStation = firestationService.updateFirestation(fireStation);
        log.info("updateFirestation response => "+ updatedFireStation);
        if (updatedFireStation != null){
            return new ResponseEntity<>(updatedFireStation, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFirestation(@RequestParam String address){
        log.info("deleteFirestation request "+ address);
        boolean removed = firestationService.deleteFirestation(address);
        log.info("deleteFirestation response "+ removed);
        if(removed){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<FireStationDTO> getPersonsCoveredByFirestation(@RequestParam int stationNumber){
        log.info("getPersonsCoveredByFirestation request "+ stationNumber);
        FireStationDTO result = firestationService.getPersonsCoveredByFirestation(stationNumber);
        if (result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
