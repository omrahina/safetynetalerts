package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.service.PhoneAlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    PhoneAlertService phoneAlertService;

    public PhoneAlertController(PhoneAlertService phoneAlertService){
        this.phoneAlertService = phoneAlertService;
    }

    @GetMapping
    public Iterable<String> getPhones(@RequestParam int firestation){
        return phoneAlertService.getPhones(firestation);
    }
}
