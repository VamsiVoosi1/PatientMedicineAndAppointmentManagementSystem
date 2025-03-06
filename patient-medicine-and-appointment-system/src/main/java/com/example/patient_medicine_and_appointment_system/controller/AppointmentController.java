package com.example.patient_medicine_and_appointment_system.controller;

import com.example.patient_medicine_and_appointment_system.entity.Appointment;
import com.example.patient_medicine_and_appointment_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/showform")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointmentbooking";
    }

    @PostMapping("/submit-appointment")
    public String submitAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "patienthome";
    }

    @GetMapping("/viewallappointments")
    public String listAppointments(Model model) {
        List<Appointment> appointmentList = appointmentService.getAllAppointments();
        model.addAttribute("appointmentList", appointmentList);
        return "appointmentlist";
    }
    @GetMapping("/viewdoctorappointments")
    public String listOfDoctorAppointments(Model model) {
        List<Appointment> appointmentList = appointmentService.getAllAppointments();
        model.addAttribute("appointmentList", appointmentList);
        return "doctorappointmentlist";
    }
}
