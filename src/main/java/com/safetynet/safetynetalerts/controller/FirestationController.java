package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.FireStationDTO;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    FirestationService firestationService;

    public FirestationController(FirestationService firestationService){
        this.firestationService = firestationService;
    }

//    @GetMapping
//    public Iterable<FireStation> list(){
//        return firestationService.list();
//    }

    @GetMapping
    public FireStationDTO getPersonsCoveredByFirestation(@RequestParam int stationNumber){
        return firestationService.getPersonsCoveredByFirestation(stationNumber);
    }

    @PostMapping
    public FireStation addFirestation(@RequestBody FireStation fireStation){
        return firestationService.addFirestation(fireStation);
    }

    @PutMapping
    public FireStation updatedFirestation(@RequestBody FireStation fireStation){
        return firestationService.updateFirestation(fireStation);
    }

    @DeleteMapping
    public void deleteFirestation(@RequestParam String address){
        firestationService.deleteFirestation(address);
    }

}
