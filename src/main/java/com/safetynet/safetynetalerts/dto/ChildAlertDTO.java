package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildAlertDTO {
    private List<ChildDTO> children;
    private List<Person> familyMembers;
}
