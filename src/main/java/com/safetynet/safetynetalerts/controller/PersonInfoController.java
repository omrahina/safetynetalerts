package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.PersonInfoDTO;
import com.safetynet.safetynetalerts.service.PersonInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personInfo")
public class PersonInfoController {

    PersonInfoService personInfoService;

    public PersonInfoController(PersonInfoService personInfoService){
        this.personInfoService = personInfoService;
    }

    @GetMapping
    public Iterable<PersonInfoDTO> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName){
        return personInfoService.getPersonInfo(firstName, lastName);
    }
}
