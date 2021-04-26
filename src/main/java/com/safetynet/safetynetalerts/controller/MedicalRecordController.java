package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public Iterable<MedicalRecord> list(){
        return medicalRecordService.list();
    }

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @PutMapping
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName){
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
