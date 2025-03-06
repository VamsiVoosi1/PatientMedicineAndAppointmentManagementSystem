package com.example.patient_medicine_and_appointment_system.controller;

import com.example.patient_medicine_and_appointment_system.Dto.PatientDto;
import com.example.patient_medicine_and_appointment_system.entity.Doctor;
import com.example.patient_medicine_and_appointment_system.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroy session
        return "doctorlogoutconfirmation"; // Redirect to confirmation page

    }
    @GetMapping("/doctorlogin")
    public String displayDoctorLogin(){
        return "doctorlogin";
    }

    @GetMapping("/doctorhome")
    public String displayPatientHome(){
        return "doctorhome";
    }

    @GetMapping("/doctorregistration")
    public String showRegistrationForm(Model model) {
        Doctor doctor=new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctorregistration"; // This should match your Thymeleaf template name
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) {
        Optional<Doctor> existingDoctorOpt=Optional.ofNullable(doctorService.findByEmail(doctor.getEmail()));
        if(existingDoctorOpt.isPresent()){
            result.rejectValue("email","email.exist","This Email is already Exist");
        }
        if(result.hasErrors()){
            model.addAttribute("doctor",doctor);
            return "doctorregistration";
        }
        doctorService.saveDoctor(doctor);
        return "redirect:/patient/patientlogin"; // Redirect to a success page
    }
    @PostMapping("/loginWithEmailAndPassword")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (doctorService.authenticate(email, password)) {
            session.setAttribute("loggedInUser", email);
            return "redirect:/doctor/doctorhome"; // Redirect to homepage after login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "doctorlogin"; // Show login page again with an error message
        }
    }
}
