package com.example.patient_medicine_and_appointment_system.repository;

import com.example.patient_medicine_and_appointment_system.entity.Doctor;
import com.example.patient_medicine_and_appointment_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByEmail(String email);

}
