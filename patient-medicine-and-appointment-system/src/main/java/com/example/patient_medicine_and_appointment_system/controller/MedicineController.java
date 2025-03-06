package com.example.patient_medicine_and_appointment_system.controller;

import com.example.patient_medicine_and_appointment_system.entity.Medicine;
import com.example.patient_medicine_and_appointment_system.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicine")

public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/add")
    public String showAddMedicineForm(@RequestParam String patientName,
                                      @RequestParam String age,
                                      @RequestParam String gender,
                                      Model model) {
        model.addAttribute("patientName", patientName);
        model.addAttribute("age", age);
        model.addAttribute("gender", gender);
        model.addAttribute("medicines", new Medicine()); // Ensure medicine object exists

        return "addmedicine"; // This should match the Thymeleaf template name (add-medicine.html)
    }
    @PostMapping("/adds")
    public String addMedicine(@ModelAttribute("medicines") Medicine medicine1,
                              @RequestParam("patientName") String patientName,
                              @RequestParam("age") String age,
                              @RequestParam("gender") String gender) {
        medicine1.setPatientName(patientName);
        medicine1.setAge(age);
        medicine1.setGender(gender);

        medicineService.saveMedicine(medicine1);

        return "redirect:/appointment/viewdoctorappointments"; // Redirect back to appointments list
    }

    @GetMapping("/patientmedicinelist")
    public String medicineList(Model model){
        List<Medicine> medicineList= medicineService.medicineList();

        if (medicineList.isEmpty()) {
            model.addAttribute("message", "No medicines found.");
        } else {
            model.addAttribute("medicines",medicineList);
        }
        return "patientmedicinelist";
    }



}