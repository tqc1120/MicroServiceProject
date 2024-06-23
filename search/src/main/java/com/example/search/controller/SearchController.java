package com.example.search.controller;

import com.example.common.domain.GeneralResponse;
import com.example.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SearchController {

    private final RestTemplate restTemplate;
    private final ResponseUtil responseUtil;
    private final ExecutorService executorService;

    @Autowired
    public SearchController(RestTemplate restTemplate, ResponseUtil responseUtil) {
        this.restTemplate = restTemplate;
        this.responseUtil = responseUtil;
        this.executorService = Executors.newCachedThreadPool();
    }

    @GetMapping("/weather/search")
    public CompletableFuture<ResponseEntity<?>> getDetails(@RequestParam String city) {
        return CompletableFuture.supplyAsync(() -> {
            String detailsServiceUrl = "http://details/details?city=" + city;
            String response = restTemplate.getForObject(detailsServiceUrl, String.class);

            try {
                GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
                return new ResponseEntity<>(generalResponse, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }, executorService);
    }

    @GetMapping("/weather/port")
    public CompletableFuture<ResponseEntity<?>> getWeatherServicePort() {
        return CompletableFuture.supplyAsync(() -> {
            String detailsServiceUrl = "http://details/details/port";
            String response = restTemplate.getForObject(detailsServiceUrl, String.class);
            try {
                GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
                return new ResponseEntity<>(generalResponse, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }, executorService);
    }

    @GetMapping("/library/authors/port")
    public CompletableFuture<ResponseEntity<?>> getLibraryServiceAuthorPort() {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/authors/port";
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            try {
                GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
                return new ResponseEntity<>(generalResponse, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }, executorService);
    }

    @GetMapping(value = "/library/authors", params = "id")
    public CompletableFuture<ResponseEntity<?>> getAuthorById(@RequestParam("id") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/authors?id=" + String.valueOf(id);
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
//            try {
//                GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
//                return new ResponseEntity<>(generalResponse, HttpStatus.OK);
//            } catch (Exception e) {
//                return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping("/library/books/port")
    public CompletableFuture<ResponseEntity<?>> getLibraryServiceBookPort() {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/books/port";
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            try {
                GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
                return new ResponseEntity<>(generalResponse, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }, executorService);
    }

    @GetMapping(value = "/library/books", params = "id")
    public CompletableFuture<ResponseEntity<?>> getBookById(@RequestParam("id") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/books?id=" + String.valueOf(id);
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
//            try {
//                GeneralResponse generalResponse = responseUtil.deserializeResponse(response);
//                return new ResponseEntity<>(generalResponse, HttpStatus.OK);
//            } catch (Exception e) {
//                return new ResponseEntity<>("Failed to parse response", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }
}
