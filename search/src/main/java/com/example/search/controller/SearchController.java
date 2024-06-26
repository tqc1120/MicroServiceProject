package com.example.search.controller;

import com.example.common.domain.GeneralResponse;
import com.example.common.util.ResponseUtil;
import com.example.library.domain.dto.AuthorDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @HystrixCommand(fallbackMethod = "fallbackGetDetails")
    public CompletableFuture<ResponseEntity<?>> getDetails(@RequestParam String city) {
        return CompletableFuture.supplyAsync(() -> {
            String detailsServiceUrl = "http://details/details?city=" + city;
            String response = restTemplate.getForObject(detailsServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping("/weather/port")
    @HystrixCommand(fallbackMethod = "fallbackGetWeatherServicePort")
    public CompletableFuture<ResponseEntity<?>> getWeatherServicePort() {
        return CompletableFuture.supplyAsync(() -> {
            String detailsServiceUrl = "http://details/details/port";
            String response = restTemplate.getForObject(detailsServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping("/library/authors/port")
    @HystrixCommand(fallbackMethod = "fallbackGetLibraryServiceAuthorPort")
    public CompletableFuture<ResponseEntity<?>> getLibraryServiceAuthorPort() {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/authors/port";
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping(value = "/library/authors", params = "id")
    @HystrixCommand(fallbackMethod = "fallbackGetAuthorById")
    public CompletableFuture<ResponseEntity<?>> getAuthorById(@RequestParam("id") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/authors?id=" + String.valueOf(id);
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping(value = "/library/authors", params = "name")
    @HystrixCommand(fallbackMethod = "fallbackGetAuthorByName")
    public CompletableFuture<ResponseEntity<?>> getAuthorByName(@RequestParam("name") String name) {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/authors?name=" + String.valueOf(name);
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping("/library/books/port")
    @HystrixCommand(fallbackMethod = "fallbackGetLibraryServiceBookPort")
    public CompletableFuture<ResponseEntity<?>> getLibraryServiceBookPort() {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/books/port";
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping(value = "/library/books", params = "id")
    @HystrixCommand(fallbackMethod = "fallbackGetBookById")
    public CompletableFuture<ResponseEntity<?>> getBookById(@RequestParam("id") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/books?id=" + String.valueOf(id);
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }

    @GetMapping(value = "/library/books", params = "title")
    @HystrixCommand(fallbackMethod = "fallbackGetBookByName")
    public CompletableFuture<ResponseEntity<?>> getBookByName(@RequestParam("title") String title) {
        return CompletableFuture.supplyAsync(() -> {
            String libraryServiceUrl = "http://library/books?title=" + String.valueOf(title);
            String response = restTemplate.getForObject(libraryServiceUrl, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }, executorService);
    }
}
