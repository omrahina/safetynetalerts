package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
@Slf4j
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public ResponseEntity<Iterable<MedicalRecord>> list(){
        log.info("Request list medical records");
        Iterable<MedicalRecord> medicalRecords = medicalRecordService.list();
        log.info("List medical records response => "+medicalRecords);
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        log.info("addMedicalRecord request "+medicalRecord);
        MedicalRecord addedRecord = medicalRecordService.addMedicalRecord(medicalRecord);
        log.info("addMedicalRecord response "+addedRecord);
        if(addedRecord != null){
            return new ResponseEntity<>(addedRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        log.info("updateMedicalRecord request "+medicalRecord);
        MedicalRecord updatedRecord = medicalRecordService.updateMedicalRecord(medicalRecord);
        log.info("updateMedicalRecord response => "+updatedRecord);
        if (updatedRecord != null){
            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName){
        log.info("Request deleteMedicalRecord "+ firstName + " " +lastName);
        boolean removed = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        log.info("Response deleteMedicalRecord => "+removed);
        if(removed){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
