package com.example.library.service;

import com.example.library.domain.dto.AuthorDto;
import com.example.library.domain.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    AuthorDto getAuthorById(Long id);
    List<AuthorDto> getAuthorsByName(String name);
    BookDto getBookById(Long id);
    List<BookDto> getBooksByTitle(String title);
}
