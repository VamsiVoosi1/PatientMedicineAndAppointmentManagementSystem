package com.example.patient_medicine_and_appointment_system.service;

import com.example.patient_medicine_and_appointment_system.Dto.PatientDto;
import com.example.patient_medicine_and_appointment_system.entity.Patient;
import com.example.patient_medicine_and_appointment_system.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto savePatient(PatientDto patientDto) {
        Patient patient=modelMapper.map(patientDto,Patient.class);
        //Password Encryption Before saving
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        Patient savedPatient=patientRepository.save(patient);

        return modelMapper.map(savedPatient,PatientDto.class);
    }

    @Override
    public PatientDto findByEmail(String email) {
        Optional<Patient> optionalPatient=patientRepository.findByEmail(email);
        return optionalPatient.map(patient -> modelMapper.map(patient,PatientDto.class)).orElse(null);

    }

    @Override
    public Patient getPatientById(Long patientId) {
        Optional<Patient> existingId=patientRepository.findById(patientId);
        return modelMapper.map(existingId,Patient.class);
    }

    public boolean authenticate(String email,String rawPassword){
        PatientDto patientDto=findByEmail(email);
        return (patientDto!=null && passwordEncoder.matches(rawPassword,patientDto.getPassword()));
    }

}
