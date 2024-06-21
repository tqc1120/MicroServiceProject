package com.example.search.controller;

import com.example.common.domain.GeneralResponse;
import com.example.common.util.ResponseUtil;
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
    private final ResponseUtil responseUtil;

    @Autowired
    public SearchController(RestTemplate restTemplate, ResponseUtil responseUtil) {
        this.restTemplate = restTemplate;
        this.responseUtil = responseUtil;
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
            GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
            return new ResponseEntity<>(generalResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/weather/port")
    public ResponseEntity<?> getWeatherServicePort() {
        String detailsServiceUrl = "http://details/details/port";
        String response = restTemplate.getForObject(detailsServiceUrl, String.class);

        try {
            GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
            return new ResponseEntity<>(generalResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
