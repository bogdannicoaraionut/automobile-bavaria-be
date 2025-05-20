package com.automobilebavaria.backend.service;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CarService {

    private final String API_KEY = "DA0bszFV++KQl9FMQbk0Hg==HmQ5Pz01dA3iAyxf";
    private final String API_URL = "https://api.api-ninjas.com/v1/carmakes";

    public String getCarMakers() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                API_URL, HttpMethod.GET, entity, String.class
        );

        return response.getBody();
    }

}
