package com.example.patient_medicine_and_appointment_system.service;

import com.example.patient_medicine_and_appointment_system.Dto.PatientDto;
import com.example.patient_medicine_and_appointment_system.entity.Doctor;
import com.example.patient_medicine_and_appointment_system.entity.Patient;
import com.example.patient_medicine_and_appointment_system.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();




    public Doctor findByEmail(String email) {
        Optional<Doctor> optionalDoctor=doctorRepository.findByEmail(email);
        return optionalDoctor.orElse(null);

    }

    public void saveDoctor(Doctor doctor) {
        //Password Encryption Before saving
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        Doctor savedDoctor=doctorRepository.save(doctor);
    }

    public boolean authenticate(String email,String rawPassword){
        Doctor doctor=findByEmail(email);
        return (doctor!=null && passwordEncoder.matches(rawPassword,doctor.getPassword()));
    }
}
