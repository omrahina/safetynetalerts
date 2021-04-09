package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirestationController {

    FirestationService firestationService;

    public FirestationController(FirestationService firestationService){
        this.firestationService = firestationService;
    }

    @GetMapping("/firestation")
    public Iterable<FireStation> list(){
        return firestationService.list();
    }

    @PostMapping("/firestation")
    public FireStation addFirestation(@RequestBody FireStation fireStation){
        return firestationService.addFirestation(fireStation);
    }

    @PutMapping("/firestation")
    public FireStation updatedFirestation(@RequestBody FireStation fireStation){
        return firestationService.updateFirestation(fireStation);
    }

}
