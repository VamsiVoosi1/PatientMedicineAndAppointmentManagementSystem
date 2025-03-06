package com.example.patient_medicine_and_appointment_system.repository;

import com.example.patient_medicine_and_appointment_system.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
}
