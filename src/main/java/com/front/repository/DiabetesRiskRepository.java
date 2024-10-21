package com.front.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import org.springframework.web.client.RestTemplate;

@Repository
public class DiabetesRiskRepository {

    private final RestTemplate restTemplate;

    @Value("${microservice.diabetes-risk-service-url}")
    private String diabetesRiskServiceUrl;

    public DiabetesRiskRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
    public String getDiabetesRisk(Long patientId) {
        String url = diabetesRiskServiceUrl + "/diabetes-risk/" + patientId;
        return restTemplate.getForObject(url, String.class);
    } 

    
    
    /*
    public String getDiabetesRisk(Long patientId, String sessionId) {
        String url = diabetesRiskServiceUrl + "/diabetes-risk/" + patientId;

        HttpHeaders headers = new HttpHeaders();
        if (sessionId != null) {
            headers.add("Cookie", "JSESSIONID=" + sessionId); 
        }

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Appeler le service via RestTemplate avec les headers
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Retourner la réponse du service (risque de diabète)
        return response.getBody();
    } */
}








/*
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DiabetesRiskRepository {

    private final RestTemplate restTemplate;

    @Value("${microservice.diabetes-risk-service-url}")
    private String diabetesRiskServiceUrl;

    public DiabetesRiskRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDiabetesRisk(Long patientId) {
        String url = diabetesRiskServiceUrl + "/diabetes-risk/" + patientId;
        return restTemplate.getForObject(url, String.class);
    }
}
*/