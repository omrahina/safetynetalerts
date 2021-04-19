package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.FireDTO;
import com.safetynet.safetynetalerts.service.FireService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fire")
public class FireController {

    FireService fireService;

    public FireController(FireService fireService){
        this.fireService = fireService;
    }

    @GetMapping
    public FireDTO getAllPersonAndStationByAddress(@RequestParam String address){
        return fireService.getAllPersonAndStationByAddress(address);
    }
}
