package com.example.patient_medicine_and_appointment_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String patientName;
    private Integer age;
    private String gender;
    private String doctor;
    private LocalDate selectedDate;
    private LocalTime selectedTime;
}
