package com.front.repository;

import com.front.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;

@Repository
public class PatientRepository {

    private final RestTemplate restTemplate;

    @Value("${microservice.patient-service-url}")
    private String patientServiceUrl;

    public PatientRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, Object>> getAllPatients() {
        String url = patientServiceUrl + "/patients";
        return restTemplate.getForObject(url, List.class);
    }

    public Patient getPatientById(Long id) {
        String url = patientServiceUrl + "/patients/" + id;
        return restTemplate.getForObject(url, Patient.class);
    }

    public void addPatient(Patient patient) {
        String url = patientServiceUrl + "/patients/add";
        restTemplate.postForObject(url, patient, Patient.class);
    }

    public void updatePatient(Long id, Patient patient) {
        String url = patientServiceUrl + "/patients/update/" + id;
        restTemplate.put(url, patient);
    }
    
    public void deletePatient(Long id) {
        String url = patientServiceUrl + "/patients/delete/" + id;
        restTemplate.delete(url);
    }
}

