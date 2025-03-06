package com.example.patient_medicine_and_appointment_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String phone;
    private LocalDate dob;
    private String password;


}
