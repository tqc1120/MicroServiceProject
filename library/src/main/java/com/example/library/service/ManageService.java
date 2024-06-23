package com.example.library.service;

import com.example.library.domain.dto.AuthorDto;

public interface ManageService {
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthorName(Long id, String newName);
    void deleteBook(Long bookId);
}
