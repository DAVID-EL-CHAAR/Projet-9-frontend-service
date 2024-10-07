package com.front.controller;

import com.front.model.Patient;
import com.front.repository.DiabetesRiskRepository;
import com.front.repository.PatientRepository;
import com.front.service.PatientService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ui")
public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final DiabetesRiskRepository diabetesRiskRepository;

    public PatientController(PatientService patientService, PatientRepository patientRepository, DiabetesRiskRepository diabetesRiskRepository) {
        this.patientRepository = patientRepository;
        this.diabetesRiskRepository = diabetesRiskRepository;
        this.patientService = patientService;
    }
    
    @GetMapping("/patients")
    public String getPatients(Model model, HttpServletRequest request) {
        List<Map<String, Object>> patients = patientRepository.getAllPatients();

        // Récupérer le cookie de session
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        // Récupérer le risque de diabète pour chaque patient
        for (Map<String, Object> patient : patients) {
            Long patientId = Long.parseLong(patient.get("id").toString());
            String diabetesRisk = diabetesRiskRepository.getDiabetesRisk(patientId, sessionId);
            patient.put("diabetesRisk", diabetesRisk);
        }

        model.addAttribute("patients", patients);
        return "patients";  // Afficher la vue "patients.html"
    }

    /*
    @GetMapping("/patients")
    public String getPatients(Model model) {
        List<Map<String, Object>> patients = patientRepository.getAllPatients();

        for (Map<String, Object> patient : patients) {
            Long patientId = Long.parseLong(patient.get("id").toString());
            String diabetesRisk = diabetesRiskRepository.getDiabetesRisk(patientId);
            patient.put("diabetesRisk", diabetesRisk);
        }

        model.addAttribute("patients", patients);
        return "patients";  // Afficher la vue "patients.html"
    }*/

    @GetMapping("/patients/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/ui/patients";
    }

    @GetMapping("/patients/edit/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "edit-patient";
    }

    @PostMapping("/patients/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/ui/patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/ui/patients";
    }
}