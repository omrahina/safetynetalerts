package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Data
public class MedicalRecordService {

    private JSONService jsonService;
    private List<MedicalRecord> medicalRecordList;

    public MedicalRecordService(JSONService jsonService){
        this.jsonService = jsonService;
        medicalRecordList = jsonService.getDataFromJSONFile().getMedicalrecords();
    }

    /**
     * Get all medical records available
     * @return A list of medical records
     */
    public Iterable<MedicalRecord> list() {
        return medicalRecordList;
    }

    /**
     * Add a medical record
     * @param medicalRecord A MedicalRecord object with at least a valid first name and last name
     * @return The added record or null if the operation failed
     */
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {

        if ((medicalRecord.getFirstName() != null) && (medicalRecord.getLastName() != null)){
            medicalRecordList.add(medicalRecord);
            log.info("Medical record successfully added");
            return medicalRecord;
        }
        log.error("Failed to add the medical record");
        return null;
    }

    /**
     * Update an existing medical record
     * @param medicalRecord A MedicalRecord object
     * @return The updated record
     */
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> updatedRecord = medicalRecordList.stream()
                .filter(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName()))
                .peek(mr -> {
                    mr.setBirthdate(medicalRecord.getBirthdate());
                    mr.setMedications(medicalRecord.getMedications());
                    mr.setAllergies(medicalRecord.getAllergies());
                })
                .findFirst();
        if (updatedRecord.isPresent()){
            log.info(medicalRecord.getFirstName() + " " +medicalRecord.getLastName() + "'s medical record successfully updated!");
            return updatedRecord.get();
        }
        log.error("Failed to update "+medicalRecord.getFirstName() + " " +medicalRecord.getLastName() + "'s medical record");
        return null;
    }

    /**
     * Delete an existing medical record
     * @param medicalRecord A MedicalRecord object
     */
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {
        boolean removed = medicalRecordList.removeIf(mr ->
                mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName()));
        if(removed){
            log.info("Medical record successfully removed");
        } else {
            log.error("Failed to remove the medical record");
        }
    }
}
