package com.example.patient_medicine_and_appointment_system.controller;

import com.example.patient_medicine_and_appointment_system.Dto.PatientDto;
import com.example.patient_medicine_and_appointment_system.entity.Patient;
import com.example.patient_medicine_and_appointment_system.service.PatientService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/home")
    public String displayIndex(){
        return "index";
    }
    @GetMapping("/services")
    public String displayServices(){
        return "services";
    }
    @GetMapping("/gallery")
    public String displayGallery(){
        return "gallery";
    }
    @GetMapping("/logins")
        public String displayLogins(){
        return "loginhome";
    }
    @GetMapping("/logouts")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroy session
        return "patientlogoutconfirmation"; // Redirect to confirmation page

    }
    @GetMapping("/patientlogin")
    public String displayPatientLogin(){
        return "patientlogin";
    }

    @GetMapping("/patienthome")
    public String displayPatientHome(){
        return "patienthome";
    }

    @GetMapping("/patientregistration")
    public String showRegistrationForm(Model model) {
        PatientDto patientDto=new PatientDto();
        model.addAttribute("patient", patientDto);
        return "patientregistration"; // This should match your Thymeleaf template name
    }
    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") PatientDto patientDto, BindingResult result,Model model) {
        Optional<PatientDto> existingPatientOpt=Optional.ofNullable(patientService.findByEmail(patientDto.getEmail()));
        if(existingPatientOpt.isPresent()){
            result.rejectValue("email","email.exist","This Email is already Exist");
        }
        if(result.hasErrors()){
            model.addAttribute("patient",patientDto);
            return "patientregistration";
        }
        patientService.savePatient(patientDto);
        return "redirect:/patient/patientlogin"; // Redirect to a success page
    }
    @PostMapping("/loginWithEmailAndPassword")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (patientService.authenticate(email, password)) {
            session.setAttribute("loggedInUser", email);
            return "redirect:/patient/patienthome"; // Redirect to homepage after login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "patientlogin"; // Show login page again with an error message
        }
    }






}
