package com.safetynet.safetynetalerts.model;

import lombok.Data;

import java.util.List;

@Data
public class JsonData {
    List<Person> persons;
    List<FireStation> firestations;
    List<MedicalRecord> medicalrecords;
}
