package com.example.search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SearchFallback {

    public ResponseEntity<?> fallbackGetDetails(String city, Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get details", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetWeatherServicePort(Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get weather service port", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetLibraryServiceAuthorPort(Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get library service author port", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetAuthorById(Long id, Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get author by ID", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetAuthorByName(String name, Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get author by name", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetLibraryServiceBookPort(Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get library service book port", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetBookById(Long id, Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get book by ID", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<?> fallbackGetBookByName(String title, Throwable throwable) {
        return new ResponseEntity<>("Fallback response: Unable to get book by title", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
