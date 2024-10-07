package com.front.service;

import com.front.model.Patient;
import com.front.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Map<String, Object>> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }

    public void addPatient(Patient patient) {
        patientRepository.addPatient(patient);
    }

    public void updatePatient(Long id, Patient patient) {
        patientRepository.updatePatient(id, patient);
    }
    
    public void deletePatient(Long id) {
        patientRepository.deletePatient(id);
    }
    
}