package com.example.patient_medicine_and_appointment_system.service;

import com.example.patient_medicine_and_appointment_system.entity.Appointment;
import com.example.patient_medicine_and_appointment_system.entity.Medicine;
import com.example.patient_medicine_and_appointment_system.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    public List<Medicine> medicineList(){
        return medicineRepository.findAll();
    }
}
