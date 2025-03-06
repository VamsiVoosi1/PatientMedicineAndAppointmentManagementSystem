package com.example.patient_medicine_and_appointment_system.repository;

import com.example.patient_medicine_and_appointment_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByEmail(String email);
}
