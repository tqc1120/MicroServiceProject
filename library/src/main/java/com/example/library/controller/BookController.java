package com.example.library.controller;

import com.example.library.domain.dto.AuthorDto;
import com.example.library.domain.dto.BookDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Value("${server.port}")
    private int randomServerPort;

//    private final SearchService searchService;
//    private final ManageService manageService;
//
//    @Autowired
//    public BookController(SearchService searchService, ManageService manageService) {
//        this.searchService = searchService;
//        this.manageService = manageService;
//    }

    @GetMapping(params = "id")
//    @Operation(summary = "Get book by ID", description = "Returns the details of a book by its ID.")
    public ResponseEntity<BookDto> getBookById(@RequestParam("id")  Long id) {
//        return new ResponseEntity<>(searchService.getBookById(id), HttpStatus.OK);
        return new ResponseEntity<>(new BookDto("Book Name", new HashSet<>()), HttpStatus.OK);
    }

    @GetMapping(params = "title")
//    @Operation(summary = "Get books by title", description = "Returns a list of books that match the given title.")
    public List<BookDto> getBooksByTitle(@RequestParam("title") String title) {
//        return searchService.getBooksByTitle(title);
        return null;
    }

    @DeleteMapping(params = "id")
//    @Operation(summary = "Delete a book by ID", description = "Deletes a book by its ID.")
    public void deleteBookById(@RequestParam("id") Long bookId) {
//        manageService.deleteBook(bookId);
    }

    @GetMapping("/port")
    public ResponseEntity<?> queryBook() {
        return new ResponseEntity<>("book service + " + randomServerPort, HttpStatus.OK);
    }
}
