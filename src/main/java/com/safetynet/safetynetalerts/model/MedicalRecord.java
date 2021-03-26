package com.safetynet.safetynetalerts.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private Date birthdate;
    private List<String> medications;
    private List<String> allergies;
}
