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

/*
package com.front.repository;

import com.front.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Repository
public class PatientRepository {

    private final RestTemplate restTemplate;

    @Value("${microservice.patient-service-url}")
    private String patientServiceUrl;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    public PatientRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, Object>> getAllPatients() {
        String url = patientServiceUrl + "/patients";

        // Créer les en-têtes avec l'authentification basique
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            List.class
        );

        return response.getBody();
    }

    public Patient getPatientById(Long id) {
        String url = patientServiceUrl + "/patients/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Patient> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            Patient.class
        );

        return response.getBody();
    }

    public void addPatient(Patient patient) {
        String url = patientServiceUrl + "/patients/add";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<Patient> requestEntity = new HttpEntity<>(patient, headers);

        restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            Patient.class
        );
    }

    public void updatePatient(Long id, Patient patient) {
        String url = patientServiceUrl + "/patients/update/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<Patient> requestEntity = new HttpEntity<>(patient, headers);

        restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            Void.class
        );
    }

    public void deletePatient(Long id) {
        String url = patientServiceUrl + "/patients/delete/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            requestEntity,
            Void.class
        );
    }
}
*/

 