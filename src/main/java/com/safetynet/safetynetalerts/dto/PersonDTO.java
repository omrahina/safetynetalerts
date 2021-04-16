package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}
