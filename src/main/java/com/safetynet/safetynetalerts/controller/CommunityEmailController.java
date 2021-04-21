package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.service.CommunityEmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    CommunityEmailService communityEmailService;

    public CommunityEmailController(CommunityEmailService communityEmailService){
        this.communityEmailService = communityEmailService;
    }

    @GetMapping
    public Iterable<String> getEmailsByCity(@RequestParam String city){
        return communityEmailService.getEmailsByCity(city);
    }
}
