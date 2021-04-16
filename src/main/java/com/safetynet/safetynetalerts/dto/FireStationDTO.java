package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireStationDTO {

    List<PersonDTO> persons;
    int numberOfAdults;
    int numberOfChildren;
}
