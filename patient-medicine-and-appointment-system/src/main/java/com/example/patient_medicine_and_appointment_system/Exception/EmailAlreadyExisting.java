package com.example.patient_medicine_and_appointment_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExisting extends RuntimeException{
    private String message;

    public EmailAlreadyExisting(String message) {
        super(message);//message to parent
        this.message = message;
    }
}