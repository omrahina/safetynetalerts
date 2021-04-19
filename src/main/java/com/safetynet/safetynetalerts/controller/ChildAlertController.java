package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.ChildAlertDTO;
import com.safetynet.safetynetalerts.service.ChildAlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    private ChildAlertService childAlertService;

    public ChildAlertController(ChildAlertService childAlertService){
        this.childAlertService = childAlertService;
    }

    @GetMapping
    public ChildAlertDTO getChildrenByAddress(@RequestParam String address){
        return childAlertService.getChildrenByAddress(address);
    }
}
