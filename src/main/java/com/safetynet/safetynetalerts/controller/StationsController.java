package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.StationsDTO;
import com.safetynet.safetynetalerts.service.StationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationsController {

    StationsService stationsService;

    public StationsController(StationsService stationsService){
        this.stationsService = stationsService;
    }

    @GetMapping
    public Iterable<StationsDTO> getFamiliesCovered(@RequestParam List<Integer> stations){
        return stationsService.getStationsCovered(stations);
    }
}
