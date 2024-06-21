package com.example.search.controller;

import com.example.common.domain.GeneralResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@RestController
public class SearchController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SearchController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

//    @GetMapping("/weather/search")
//    public ResponseEntity<?> getDetails() {
//        //TODO
//        return new ResponseEntity<>("this is search service", HttpStatus.OK);
//    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails(@RequestParam String city) {
        String detailsServiceUrl = "http://details/details?city=" + city;
        String response = restTemplate.getForObject(detailsServiceUrl, String.class);

        try {
            // Deserialize the JSON response
            Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
            Object data = responseMap.get("data");

            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setCode(0);
            generalResponse.setTimestamp(new Date());
            generalResponse.setData(data);

            return new ResponseEntity<>(generalResponse, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any deserialization errors
            return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
