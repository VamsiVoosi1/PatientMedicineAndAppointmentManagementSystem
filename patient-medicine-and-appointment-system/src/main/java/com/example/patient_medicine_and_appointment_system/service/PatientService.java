package com.example.patient_medicine_and_appointment_system.service;

import com.example.patient_medicine_and_appointment_system.Dto.PatientDto;
import com.example.patient_medicine_and_appointment_system.entity.Patient;
import org.springframework.stereotype.Service;


public interface PatientService {
    PatientDto savePatient(PatientDto patientDto);
    PatientDto findByEmail(String email);
    Patient getPatientById(Long patientId);
    boolean authenticate(String email,String rawPassword);
}
