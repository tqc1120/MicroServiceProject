package com.example.library.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Value("${server.port}")
    private int randomServerPort;

    @GetMapping("/book/port")
    public ResponseEntity<?> queryAuthor() {
        return new ResponseEntity<>("book service + " + randomServerPort, HttpStatus.OK);
    }
}
